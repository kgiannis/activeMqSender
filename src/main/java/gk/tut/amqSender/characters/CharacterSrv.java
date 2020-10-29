package gk.tut.amqSender.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CharacterSrv {

	JmsTemplate jmsTemplate;
	
	@Autowired
	public CharacterSrv(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(String destinationName, String message) {
		jmsTemplate.convertAndSend(destinationName, message);
	}
	
	public String convertObjectToString(Character character) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(character);
		} catch (JsonProcessingException e) {
			return "Error while trying to convert Object to String";
		}
	}
}
