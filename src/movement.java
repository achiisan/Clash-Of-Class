//This class contains the AI for the playable characters

class Unit extends Thread{
	double x,y, id, range=10.0;
	int hp=100,damage=10;
	Unit target;
	
	public Unit(double x, double y){
		this.x=x;
		this.y=y;
		//this.id=id;
	}
	
	public void computepos(){
		
			try {
				double slope=(target.y-y)/(target.x-x);
				double b=target.y-(target.x*slope);
				if (target.x>x) x++; else x--;
				y=((slope)*x)+b;
				System.out.println(id+": ("+x+","+y+")");
				if(Math.sqrt((target.x-x)*(target.x-x)+(target.y-y)*(target.y-y))<range){
					while(hp>0){	
						if(target.hp<=0){
							//System.out.println(id+" wins");
							hp=0;
							//replace later with target finding algorithm
							break;
						}
						//System.out.println(target.id+"'s HP: "+hp);
						target.hp--;
						
					}
				}
				if(hp==0){
					//replace later with deletion algo for unit
				
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
}

}
