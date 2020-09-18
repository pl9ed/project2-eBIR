
import com.revature.models.User;
import com.revature.services.UserService;


public class Driver {

	public static void main(String[] arg) {
		UserService us = new UserService();
		
		us.register("train","toot","Thomas","Engine","ILoveTrains@gmail.com");
		
		User user = us.login("train", "toot");
		
		us.updateFirstName(user, "MC Queen");
		System.out.println(user.toString());
		
		us.register("meme", "god", "dio", "brando", "Jojo@email.com");
		
		User user2 = us.login("meme", "god");
		us.updateLastName(user2,"Stando");
		us.updateEmail(user2, "ZaWorld@gmail.com");

	}
}
