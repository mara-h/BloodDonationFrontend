package cg.example.blooddonationfrontend.model;

public class Globals {
    public static void setCurrentUser(User currentUser) {
        Globals.currentUser = currentUser;
    }

    public static User currentUser;
}
