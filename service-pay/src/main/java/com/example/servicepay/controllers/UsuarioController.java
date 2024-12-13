package com.example.servicepay.controllers;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.servicepay.dto.AuthMessageDTO;
import com.example.servicepay.dto.CredenciaisDTO;
import com.example.servicepay.dto.TokenDTO;
import com.example.servicepay.dto.UserDTO;
import com.example.servicepay.dto.UserEmailDTO;
import com.example.servicepay.dto.UserPasswordRestDTO;
import com.example.servicepay.dto.UserResponseDTO;
import com.example.servicepay.entities.UserModel;
import com.example.servicepay.exceptions.SenhaInvalidaException;
import com.example.servicepay.exceptions.UsuarioException;
import com.example.servicepay.security.jwt.JwtService;
import com.example.servicepay.service.EmailService;
import com.example.servicepay.service.UsuarioServiceImpl;
import com.example.servicepay.util.CurrentUser;
import com.example.servicepay.util.LoggedInUser;
import com.example.servicepay.util.TemplateName;
import com.example.servicepay.util.TokenUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    
    private final EmailService emailService;
    
    @Value("${security.url.account.reset}")
    private String urlAccountReset;

    @Value("${security.url.account.confirmation.send}")
    private String urlAccountConfirmation;
    
    
        	  
    @Operation(summary = "Save a user and send email to confirm account.")
    @ApiResponses({
    	@ApiResponse( responseCode = "201",description = "User saved sussefully."),
    	@ApiResponse( responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save( @RequestBody @Valid UserDTO userDTO ) throws MessagingException{
    	 UserModel user = new UserModel();
    	 
    	boolean isPasswordEqual = user.isPasswordEquals(userDTO.getPassword(), userDTO.getPasswordConfirmed());
        
    	if(isPasswordEqual) {
	    	String senhaCriptografada = passwordEncoder.encode(userDTO.getPassword());
	        
	        userDTO.setPassword(senhaCriptografada);
	        user = modelMapper.map(userDTO, UserModel.class);
	        String token = jwtService.gerarToken(user);
	        
	        user.setActivated(false);
	        user.setTokenConfirmedAccount(token);
	        
	        UserModel userSaved = new UserModel();
	     
            userSaved = usuarioService.salvar(user);
    
	        //Send email to the user with this address.
	    
		    Map<String, Object> messageTemplate = new HashMap<>();
		    messageTemplate.put("link_with_confirmation_token", urlAccountConfirmation+userSaved.getTokenConfirmedAccount());
		    messageTemplate.put("username", userSaved.getName());
		    
		    emailService.sendEmail(
		    		userSaved.getEmail(), 
		    		"CONFIRM ACCOUNT INSTRUCTION", 
		    		TemplateName.CONFIRMATION_INSTRUCTIONS, messageTemplate);
		    
	        userDTO.setId(userSaved.getId());
	        UserResponseDTO useResponseDto = modelMapper.map(userDTO, UserResponseDTO.class);
	        return useResponseDto;
        }
    	throw new UsuarioException("Password is diferent: "+ userDTO.getEmail());
        
    }
    
    
   @Operation(summary ="Resend account confirmed email to confirm account.")
    @ApiResponses({
    	@ApiResponse( responseCode = "201",description = "User saved sussefully."),
    	@ApiResponse( responseCode = "401",description = "")
    })
    @PostMapping(path = "/account/confirmed/resend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AuthMessageDTO accountConfirmedResend(@Valid @RequestBody UserEmailDTO userEmail ) throws MessagingException{
    
    	UserModel usuario = new UserModel();
        usuario.setEmail(userEmail.getEmail());
        
	    UserModel usuarioAutenticado = usuarioService.autenticarEmail(usuario);
	    String token = jwtService.gerarToken(usuarioAutenticado);
	    TokenDTO tokenReceived = new TokenDTO(usuario.getEmail(), token);
	    
	    usuarioAutenticado.setTokenConfirmedAccount(token);
	    this.usuarioService.salvar(usuarioAutenticado);
	    
	    //Send email to the user with this address.

	    Map<String, Object> messageTemplate = new HashMap<>();
	    messageTemplate.put("username", usuarioAutenticado.getName());
	    messageTemplate.put("link_with_confirmation_token", urlAccountConfirmation+tokenReceived.getToken());
	   
	    emailService.sendEmail(
	    		usuarioAutenticado.getEmail(), 
	    		"CONFIRM ACCOUNT INSTRUCTION", 
	    		TemplateName.CONFIRMATION_INSTRUCTIONS, messageTemplate);
	    
	    
	    System.out.println(urlAccountConfirmation+tokenReceived.getToken());
  
	    return AuthMessageDTO
	    		.builder()
	    			.message("An email confirmation was sent to you.")
	    		.build();
         
    }
    
   @Operation(summary ="Confirme account created")
    //@ApiResponses({
    	//@ApiResponse( responseCode = "200",description = "Account confirmated successfully."),
    	//@ApiResponse( responseCode = "401",description = "Can not confirme your account. Token does not exit.")
    //})
    @PostMapping(path = "/account/confirmed", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthMessageDTO accountConfirm(@RequestParam("token") String token){
    
    	UserModel user = this.usuarioService.findByTokenConfirmAccount(token);
    	if(user != null) {
    	   user.setActivated(true);
     	   this.usuarioService.salvar(user);
    	}else {	
    	throw new UsuarioException("User do not exist or token has exprired.");
    	}
    	
	    return AuthMessageDTO
	    		.builder()
	    			.message("Your account was successfully confirmed.")
	    		.build();
    }
    

   @Operation(summary ="Authenticate the user and return a token to access API resourses.")
    //@ApiResponses({
    	//@ApiResponse( responseCode = "200",description = "User authenticated successfully."),
    	//@ApiResponse( responseCode = "401",description = "Can invalide credential or you need to verificate your account to access.")
    //})
    @PostMapping("/auth")
    public TokenDTO autenticar(@Valid @RequestBody CredenciaisDTO credenciais){
        try{
            
        	UserModel usuario = new UserModel();
                    usuario.setEmail(credenciais.getEmail());
                    usuario.setPassword(credenciais.getPassword());
            
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
        	throw new SenhaInvalidaException();
        }
    }
    
   @Operation(summary ="Verify if user exist and send the reset password instructions.")
    //@ApiResponses({
    	//@ApiResponse( responseCode = "200",description = "Instruction sent successfully."),
    	//@ApiResponse( responseCode = "401",description = "Cannot send the email instructions to create a new password.")
    //})
    @PostMapping("/password/new")
    public AuthMessageDTO passwordNew(@RequestBody UserEmailDTO userEmail) throws MessagingException{
        try{
            
        	UserModel usuario = new UserModel();
                    usuario.setEmail(userEmail.getEmail());
                
            UserModel usuarioAutenticado = usuarioService.autenticarEmail(usuario);
            String token = jwtService.gerarToken(usuarioAutenticado);
            TokenDTO tokenReceived = new TokenDTO(usuario.getEmail(), token);
            
            usuarioAutenticado.setTokenResetPassword(token);
            this.usuarioService.salvar(usuarioAutenticado);
            
            //Send email to the user with this address.
    	    Map<String, Object> messageTemplate = new HashMap<>();
    	    messageTemplate.put("username", usuarioAutenticado.getName());
    	    messageTemplate.put("link_reset_password_token", urlAccountReset+tokenReceived.getToken());
    	   
    	    emailService.sendEmail(
    	    		usuarioAutenticado.getEmail(), 
    	    		 "RESET PASSWORD INSTRUCTION", 
    	    		TemplateName.RESET_PASSWORD_INSTRUCTIONS, messageTemplate);
            
            System.out.println(urlAccountReset+tokenReceived.getToken());
            
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

	    return AuthMessageDTO
	    		.builder()
	    			.message("We sent an email instruction to reset your password. Please open your email and click de link.")
	    		.build();

    }
    

   @Operation(summary ="Verify if user TokenResetPassword exist and change the password.")
    @ApiResponses({
    	@ApiResponse( responseCode = "200", description = "Instruction sent successfully."),
    	@ApiResponse( responseCode = "401",description = "Cannot send the email instructions to create a new password.")
    })
    @PostMapping("/password/reset")
    public AuthMessageDTO passowrdReset(@RequestBody UserPasswordRestDTO userPasswordRestDTO, @RequestParam("token") String token){
    
    	UserModel user = this.usuarioService.findByTokenResetPassword(token);
    	boolean tokenEquals = user.isTokenEquals(user.getTokenResetPassword(), token);
    	
    	if(tokenEquals) {
    		boolean passwordEqual = 
    				user.isPasswordEquals(
    						userPasswordRestDTO.getNewPassword(), 
    						userPasswordRestDTO.getConfirmePassword());
    		
    		if(passwordEqual) {
    		   String senhaCriptografada = passwordEncoder.encode(userPasswordRestDTO.getNewPassword()); 
    		   
    		   user.setTokenResetPassword(TokenUtil.NO_TOKEN_GENERATED);
    		   user.setPassword(senhaCriptografada);

    		   this.usuarioService.salvar(user);
    		}else {
    			throw new UsuarioException("Password is diferent.");
    		}
    	}

	    return AuthMessageDTO
	    		.builder()
	    			.message("Your password was successfully updated.")
	    		.build();
    }
    
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    
    @GetMapping("/current_user")
	public CurrentUser getAuthenticatedUser(@LoggedInUser CurrentUser authentication) {
	
			return authentication;
	}

}
