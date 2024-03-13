
public class user {
    String userid, name, contact;
    String [] borrowedBooks;

    public user(){
        this.userid = "";
        this.name = "";
        this.contact = "";
        this.borrowedBooks = new String[5];
        for (int i = 0; i < borrowedBooks.length; i++) {
            this.borrowedBooks[i] = "";
        }
    }
    public static void main(String[] args) {
        
    }
}
