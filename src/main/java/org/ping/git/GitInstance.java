/**
 * Only one instance of this class should ever exist. A GitInstance is an instance of the login for one user. There should only be one person logged in per
 * instance of PING.
 * 
 * 
 */
package org.ping.git;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;


/**
 * Once a person logs in with their credentials, this class will gather their bio, as well as all the repositories they currently own.
 * 
 * @author schristou88
 *
 */
/*h
 * TODO: Remove this class and allow for DI with multiple git interfaces, this being the interface.
 * 1. To change this class to DI, first change this class to an interface.
 * 2. Port all github items to it's own class which extends this interface.
 * 3. Create other instance for other classes.
 */
public class GitInstance {
	
	//Singleton class
	private GitInstance() {
		service = null;
		config = null;
		client = null;
		avatar = null;
	}
	
	private static class GitInstanceHolder {
		public static final GitInstance INSTANCE = new GitInstance();
	}
	
	public static GitInstance getInstance() {
		return GitInstanceHolder.INSTANCE;
	}
	
	public void login(String userName, char[] cs) {
		client = new GitHubClient();
		String pwd = new String(cs);
		client.setCredentials(userName, pwd);
		service = new RepositoryService(client);
		config =  new UserService(client);
		avatar = null;
		
		// Clean out the password securely
		for(int i = 0; i < cs.length; i++) {
			pwd.replace(cs[i], '0');
			cs[i] = 0;
		}
		cs = null;
		pwd = null;
	}
	
	/**
	 * Get the list of public AND private repositories for current user.
	 * 
	 * @return List of repositories for current user
	 * @throws IOException Http Error.
	 */
	public List<Repository> getRepos() throws IOException {
		return service.getRepositories();
	}
		
	public boolean  isConnected() throws IOException {
		return config.getUser() != null;
	}
	
	/**
	 * Gets the avatar for the specific user.
	 * 
	 * @return Image of the avatar.
	 * @return null if failed to login or no avatar image.
	 */
	public Image getAvatar() throws IOException {
	//TODO: For production uncomment this block of code
		
//		//Download Avatar from the tubes
//		try {
//			if (config != null && config.getUser() != null) {
//				URL gravitarURL = new URL(config.getUser().getAvatarUrl());
//				avatar = ImageIO.read(gravitarURL);
//			}
//			
//		} catch (IOException e) {
//			LOGGER.log(Level.WARNING, "Error when connecting to avatar url: ", e);
//			try {
//				avatar = ImageIO.read(new File("src/main/resources/icons/defaultAvatar.png"));
//			} catch (IOException e1) {
//				avatar = null;
//				//NEVER should hit here. Only instance it would hit here is if it failed to load
//				//default avatar.
//			}
//			
//		}
		
		avatar = ImageIO.read(new File("src/main/resources/icons/defaultAvatar.png"));
		
		return avatar;
	}
	
	private RepositoryService service;
	private UserService config;
	private GitHubClient client;
	private Image avatar;
	
	private static final Logger LOGGER = Logger.getLogger(GitInstance.class.getCanonicalName());
}