package requests;

/**
 *
 * @author root
 */
public class DeleteRequest {
    // private boolean isAdmin;
    private int index;

    public DeleteRequest(int index) {
        //   this.isAdmin = isAdmin;
        this.index = index;
    }

//    public boolean isIsAdmin() {
//        return isAdmin;
//    }
//
//    public void setIsAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
