package edu.devit.entities;

public class Freelancer {


    private int id;
    private String email,name,password,photo,title,skills,country,experience;
    private int prix;


    public Freelancer() {
    }

    public Freelancer(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Freelancer(int id, String email, String name, String password, String photo, String title, String skills, String country, String experience, int prix) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.title = title;
        this.skills = skills;
        this.country = country;
        this.experience = experience;
        this.prix = prix;
    }


    public Freelancer(String email, String name, String password, String photo, String title, String skills, String country, int prix ,String experience) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.title = title;
        this.skills = skills;
        this.country = country;
        this.prix = prix;
        this.experience = experience;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", title='" + title + '\'' +
                ", skills='" + skills + '\'' +
                ", country='" + country + '\'' +
                ", experience='" + experience + '\'' +
                ", prix=" + prix +
                '}';
    }
}
