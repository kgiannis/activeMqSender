package gk.tut.amqSender.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CharacterCtrl {
	
	private final Logger logger = LoggerFactory.getLogger(CharacterCtrl.class);

	CharacterSrv characterSrv;
	
	@Autowired
	public CharacterCtrl(CharacterSrv characterSrv) {
		this.characterSrv = characterSrv;
	}
	
	@Value("${destination.name}")
	private String destinationName;
	
	@PostMapping("/message/")
	public ResponseEntity<String> sendMessageAsRequestBody(@RequestBody final Character message){
		String messageAsString = characterSrv.convertObjectToString(message);
		logger.info("Sending: {} ", messageAsString);
		characterSrv.sendMessage(destinationName, messageAsString);
		return new ResponseEntity<String>(messageAsString, HttpStatus.OK);
	}
	
	@PostMapping("/message/{message}")
	public ResponseEntity<String> sendMessageAsPathVariable(@PathVariable final String message){
		logger.info("Sending: {} ", message);
		characterSrv.sendMessage(destinationName, message);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
