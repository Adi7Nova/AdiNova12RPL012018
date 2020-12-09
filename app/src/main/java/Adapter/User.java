package Adapter;

public class User {
    private String Username;
    private String NoKTP;
    private String NoHP;
    private String Email;
    private String Roleuser;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNoKTP() {
        return NoKTP;
    }

    public void setNoKTP(String noKTP) {
        NoKTP = noKTP;
    }

    public String getNoHP() {
        return NoHP;
    }

    public void setNoHP(String noHP) {
        NoHP = noHP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoleuser() {
        return Roleuser;
    }

    public void setRoleuser(String roleuser) {
        Roleuser = roleuser;
    }
}
