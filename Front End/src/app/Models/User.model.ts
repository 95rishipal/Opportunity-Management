export class User {
    public userid: number;
    public email: string;
    public pass: string;
    public gid: string;
    public constructor(userid: number, email: string, pass: string, gid: string){
        this.userid = userid;
        this.email = email;
        this.pass = pass;
        this.gid =  gid;
    }
}