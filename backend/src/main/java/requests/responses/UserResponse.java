package requests.responses;

public class UserResponse {

    /* Too lazy to make getters and too much code (i know, i know: ctrl + shift + insert) */
    public final Long userId;
    public final String username;
    public final String email;
    public final String profilePhotoLink;

    public UserResponse(Long userId, String username, String email, String profilePhotoLink) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profilePhotoLink = profilePhotoLink;
    }
}
