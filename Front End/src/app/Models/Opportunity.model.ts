export class Opportunity {
	public oppid: number;
	public description: String;
	public endDate: String;
    public skills: any;
    public location: String;
    public minxp: number;
    public demand: number;
    public constructor(){
       
    }

    public default(){
        this.oppid = 0;
        this.description = "";
        this.endDate = "";
        this.skills = "";
        this.location = "";
        this.minxp = 0;
        this.demand = 0;
 }
}
