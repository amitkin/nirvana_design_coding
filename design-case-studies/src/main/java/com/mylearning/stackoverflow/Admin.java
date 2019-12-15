package com.mylearning.stackoverflow;

public class Admin extends Member {
    public boolean blockMember(Member member) {
        return true;
    }

    public boolean unblockMember(Member member){
        return true;
    }
}