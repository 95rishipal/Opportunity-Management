export class Opportunity {
	public oppid: number;
	public discription: String;
	public endDate: String;
    public skills: any;
    public constructor(){
       
    }

    public default(){
        this.oppid = 0;
        this.discription = "";
        this.endDate = "";
        this.skills = "";
 }
}
