import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer
{
	TelegramBot bot = TelegramBotAdapter.build("688036318:AAHlzji1ESYrCMAz16t_7KzAzFE7GGQK3WM");
	//Object that receives messages
	GetUpdatesResponse updatesResponse;
	//Object that send responses
	SendResponse sendResponse;
	//Object that manage chat actions like "typing action"
	BaseResponse baseResponse;
	int queuesIndex=0;
	ControllerSearch controllerSearch; //Strategy Pattern -- connection View -> Controller
	boolean searchBehaviour = false;
	private Model model;
	
	public View(Model model){
		this.model = model; 
	}
	
	public void setControllerSearch(ControllerSearch controllerSearch){ //Strategy Pattern
		this.controllerSearch = controllerSearch;
	}
	
	public void receiveUsersMessages() {
		//infinity loop
		while (true){
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			//Queue of messages
			List<Update> updates = updatesResponse.updates();
			//taking each message in the Queue
			for (Update update : updates) {
				//updating queue's index
				queuesIndex = update.updateId()+1;
				
				if(this.searchBehaviour==true){
					this.callController(update);
					
				}else if(update.message().text().equals("ts")){
					setControllerSearch(new ControllerSearchTS(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"CLASSIFICAÇÃO DOS TESTES DE SOFTWARE\n" + 
							"QUANTO À ABRANGÊNCIA \n" + 
							"1-TESTES DE UNIDADE \n" + 
							"2-TESTES DE INTEGRAÇÃO \n" + 
							"3-TESTES DE SISTEMA \n" + 
							"QUANTO AO CONHECIMENTO ACERCA DO SISTEMA SOB TESTE \n" + 
							"4-TESTES CAIXA BRANCA \n" + 
							"5-TESTES CAIXA PRETA \n" + 
							"6-TESTES CAIXA CINZA"));
					this.searchBehaviour = true;
					
				} else if(update.message().text().equals("bd")){
					setControllerSearch(new ControllerSearchBD(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Normalização - Conceitos\n" + 
							"1-Grupo Repetitivo\n" + 
							"2-Dependência Funcional Parcial\n" + 
							"3-Dependência Funcional Transitiva"));
					this.searchBehaviour = true;
					
				} else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Disciplina bd ou ts"));
				}
			}
		}
	}
	
	public void callController(Update update){
		this.controllerSearch.search(update);
	}
	
	public void update(long chatId, String tssData){
		sendResponse = bot.execute(new SendMessage(chatId, tssData));
		this.searchBehaviour = false;
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}
}