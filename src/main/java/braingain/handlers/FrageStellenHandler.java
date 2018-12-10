package braingain.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import braingain.modell.Spielrunde;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FrageStellenHandler implements RequestHandler {

	private Spielrunde sr;

	public FrageStellenHandler(Spielrunde sr) {
		this.sr = sr;
	}

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("FrageStellenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = sr.fetchFrage();

		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("FrageSession", speechText)
				.withSpeech(speechText).withShouldEndSession(false).build();
	}
}