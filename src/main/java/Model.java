import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

public class Model implements Subject{
	
	private List<Observer> observers = new LinkedList<Observer>();
	
	private List<TS> tss = new LinkedList<TS>();
	private List<BD> bds = new LinkedList<BD>();
	
	private static Model uniqueInstance;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(long chatId, String tssData){
		for(Observer observer:observers){
			observer.update(chatId, tssData);
		}
	}
	
	public void addTS(TS ts){
		this.tss.add(ts);
	}
	
	public void addBD(BD bd){
		this.bds.add(bd);
	}
	
	public void searchTS(Update update){
		String tssData = null;
		for(TS ts: tss){
			if(ts.getTopico().equals(update.message().text())){
				tssData = ts.getTopico();
			}
		}
		
		if(tssData != null){
			this.notifyObservers(update.message().chat().id(), tssData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Tópico não encontrado!");
		}
	}
	
	public void searchBD(Update update){
		String bdsData = null;
		for(BD bd:bds){
			if(bd.getTopico().equals(update.message().text())) bdsData = bd.getTopico();
		}
		
		if(bdsData != null){
			this.notifyObservers(update.message().chat().id(), bdsData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Tópico não encontrado!");
		}
	}
}