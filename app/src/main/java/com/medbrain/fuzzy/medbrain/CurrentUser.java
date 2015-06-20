package com.medbrain.fuzzy.medbrain;


/**
 * Created by Ulises on 6/19/2015.
 */
public class CurrentUser {
    private static CurrentUser currentUser = new CurrentUser();

    //DatabaseHandler db = new DatabaseHandler(this);
    private int ID = 0; //usuario en este momento O el viejo

    public void setCurrentUser(int newID) {
        ID = newID;
    }

    public int getID(){
        return ID;
    }

    public static CurrentUser getInstance() {
        return currentUser;
    }
    private CurrentUser() {
    }
}
