/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.entities;

/**
 *
 * @author walid belhadj
 */
public class Test {
    private int id;
    private String nom;
    private String q1;
    private String r1;
    private String q2;
    private String r2;
    private String q3;
    private String r3;
    private String q4;
    private String r4;
    private String q5;
    private String r5;

    public Test() {
    }

    public Test(int id, String nom, String q1, String r1, String q2, String r2, String q3, String r3, String q4, String r4, String q5, String r5) {
        this.id = id;
        this.nom = nom;
        this.q1 = q1;
        this.r1 = r1;
        this.q2 = q2;
        this.r2 = r2;
        this.q3 = q3;
        this.r3 = r3;
        this.q4 = q4;
        this.r4 = r4;
        this.q5 = q5;
        this.r5 = r5;
    }

    public Test(String nom, String q1, String r1, String q2, String r2, String q3, String r3, String q4, String r4, String q5, String r5) {
        this.nom = nom;
        this.q1 = q1;
        this.r1 = r1;
        this.q2 = q2;
        this.r2 = r2;
        this.q3 = q3;
        this.r3 = r3;
        this.q4 = q4;
        this.r4 = r4;
        this.q5 = q5;
        this.r5 = r5;
    }

    public Test(int id) {
        this.id = id;
    }

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", nom=" + nom + ", q1=" + q1 + ", r1=" + r1 + ", q2=" + q2 + ", r2=" + r2 + ", q3=" + q3 + ", r3=" + r3 + ", q4=" + q4 + ", r4=" + r4 + ", q5=" + q5 + ", r5=" + r5 + '}';
    }
    
    
}
