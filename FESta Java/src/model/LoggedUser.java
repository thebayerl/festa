package model;

public class LoggedUser extends Usuario {

	// static variable single_instance of type Singleton 
    private static LoggedUser usuarioLogado = null;
        
    public static void setLoggedUser (Usuario usuario) {
    	
    	usuarioLogado = LoggedUser.getInstance();
    	usuarioLogado.setId(usuario.getId());
    	usuarioLogado.setUsername(usuario.getUsername());
    	usuarioLogado.setCpf(usuario.getCpf());
    }
  
    // static method to create instance of Singleton class 
    public static LoggedUser getInstance() 
    { 
        if (usuarioLogado == null) 
        	usuarioLogado = new LoggedUser(); 
  
        return usuarioLogado; 
    } 
}
