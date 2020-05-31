export class Opportunity {
	public oppid: number;
	public description: String;
	public endDate: String;
    public skills: any;
    public location: String;
    public constructor(){
       
    }

    public default(){
        this.oppid = 0;
        this.description = "";
        this.endDate = "";
        this.skills = "";
        this.location = "";
 }
}
