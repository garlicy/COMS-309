package com.example.cardgames.net_utils;

/**
 * @author Colton Hazlett
 * Class used to save all the request links and other important variables
 */

public class Const {
    public static final String URL_JSON_OBJECT = "https://api.androidhive.info/volley/person_object.json";
    public static final String URL_JSON_ARRAY = "https://api.androidhive.info/volley/person_array.json";
    public static final String URL_STRING_REQ = "https://api.adroidhive.info/volley/string_response.html";
    public static final String URL_IMAGE = "https://api.androidhive.info/volley/volley-image.jpg";


    public static final String URL_ADD_USER = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/register";
    public static final String URL_LOGIN_USER = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/login";
    public static final String URL_CHANGE_PASSWORD = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/changepassword";
    public static final String URL_ADD_FRIEND = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/addfriend";
    public static final String URL_REMOVE_FRIEND = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/removefriend";
    public static final String URL_GET_FRIENDS = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/getfriends";
    public static final String URL_GET_USER_INFO = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/get";
    public static final String URL_SEARCH_USERS = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/search/users";
    public static final String URL_SEARCH_GAMES = "http://coms-309-hv-4.cs.iastate.edu:8080/game/search/games";
    public static final String URL_USER_INFO = "http://coms-309-hv-4.cs.iastate.edu:8080/demo/getinfo";

    public static final String URL_GET_GAME_INFORMATION = "http://coms-309-hv-4.cs.iastate.edu:8080/game/info"; //add "?GameName=..."
    public static final String URL_GET_CATEGORIES = "http://coms-309-hv-4.cs.iastate.edu:8080/categories/get";
    public static final String URL_GET_CAT_GAMES = "http://coms-309-hv-4.cs.iastate.edu:8080/categories/games"; //add "?CategoryName=..."




    public static final String URL_ECHO_POST_GET_CAT = "https://postman-echo.com/get?total=2&name1=Casino&name2=Strategy";
    public static final String URL_ECHO_POST_GET_DEFAULT_GAMES = "https://postman-echo.com/get?total=2&name0=War&creator0=Hazlett&image0=2131230917" +
            "&name1=Golf&creator1=Jung&image1=2131230847";


    public static final String URL_ECHO_POST_GET_WAR_INFO = "https://postman-echo.com/get?isDefault=true&overview=" +
            "The point of the game is to battle your opponent, each player starts with the same amount cards and then continue to lay cards to battle." +
            "&rules=Each player has 26 cards to start, the higher card wins the battle, there is a tie if the card value is the same, and tie breakers" +
            "are 3 cards face down and then flip another card to decide.&scoring=It is simple the person with the most/all cards wins&extrainfo=" +
            "May the best person win";
    public static final String URL_ECHO_POST_GET_GOLF_INFO = "https://postman-echo.com/get?isDefault=true&" +
            "overview=The point of the game is to get the least amount of points possible, you want to out stroke your opponent. Each players gets cards and has " +
                "use their skill and luck to get the best cards possible." +
            "&rules=Each player gets dealt 4 cards and they get to know the bottom two. They then can draw from the deck. The drawn card can be placed " +
                "in their pile or can discard it and flip a card that is already on the table. You want 5's, kings, pairs and triples" +
            "&scoring=The player with the lowest score after 9 hands wins. The scoring works like 5's are worth -5 point, kings are worth 0 points, Pairs are worth 0 points, "+
                "Triples are worth negative the value of the card" +
            "&extrainfo=Try to switch out the unknown cards first";

    public static final int CARD_WIDTH = 200;
    public static final int CARD_HEIGHT = 300;
}
