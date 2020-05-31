export class User {
    public userid: number;
    public name: String;
    public email: string;
    public password: string;
    public gid: string;
    public constructor(userid: number,name: String, email: string, pass: string, gid: string){
        this.userid = userid;
        this.email = email;
        this.password = pass;
        this.gid =  gid;
        this.name = name;
    }
}