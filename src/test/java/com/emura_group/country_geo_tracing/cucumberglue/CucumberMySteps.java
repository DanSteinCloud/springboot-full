package com.emura_group.country_geo_tracing.cucumberglue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.persistence.Entity;
import reactor.core.publisher.Mono;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ClientResponse.Headers;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonPointer;

import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberMySteps {
	WebClient webClient;
    @LocalServerPort
    String port;
    ResponseEntity<String> lastResponse;
    public Mono<ClientResponse> clientResponse;
    //public Mono<Country> countryMono;
    public Headers headers;
    public int retreivedStatusCode;
    public int statusCode;
    HttpStatusCode responseCode;
   
    
//    private final WebClient webClient;
//	
//	@Autowired
//	  public CucumberMySteps(WebClient webClient) {
//	      this.webClient = webClient;
//	}
    
    //ParameterizedTypeReference<Map<Integer, Country>> responseType = new ParameterizedTypeReference<Map<Integer, Country>>() {};
    //record DataWithStatus(MultiValueMap<Integer, Country> statuscode, Country country){};

    @When("The client call the endpoint {string}")
    public void whenClientCalls(String url) {
    	try {
    		
//    		Mono<Country> countryMono = webClient.get()
//    			    .uri("http://localhost:" + port + url)
//    			    .retrieve()
//    			    .bodyToMono(Country.class);
    		

    		
            //lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null, 
                    //String.class);
//    		WebClient webClient = WebClient.builder()
//    	            .baseUrl("http://localhost:" + port)
//    	            .build();
//    		
//    		response = webClient.get()
//			        .uri("/customstatuscode")
//			        .retrieve()
//			        .bodyToMono(String.class);
//		 
//		    response.flatMap(clientResponse -> {
//			    int statusCode = clientResponse.statusCode(); // Get the status code
//			    // Process the response based on the status code
//			    return Mono.just("Response processed");

    		
//    		Mono<DataWithStatus> dataWithCookies = WebClient
//    				    .builder()
//    				    .build()
//    		            .get()
//    		            .uri("http://localhost:" + port + url)
//    		            .exchangeToMono(response -> 
//                        response.bodyToMono(String.class)
//                        .map(stringBody -> new DataWithCookies(
//                                                    stringBody, 
//                                                    response.cookies())
//                        );
             
    		responseCode = WebClient.builder()
    				.baseUrl("http://localhost:" + port + url)
    				.build()
    		        .get().exchangeToMono(response -> Mono.just(response.statusCode()))
    		        .block();
    		
    		
             clientResponse = WebClient
            				    .builder()
            				    .build()
            		            .get()
            		            .uri("http://localhost:" + port + url)
            		            .exchangeToMono(response -> {
            		                if (response.statusCode().isError()) {
            		                    return response.createException().flatMap(Mono::error);
            		                } else {
            		                	retreivedStatusCode = response.statusCode().value();
            		                    var headers = response.headers().asHttpHeaders();
            		                    var pointer = JsonPointer.compile(url);
            		                    //var body = response.body(streamingJsonBodyExtractor.toFlux(Entity.class, pointer));

            		                    return Mono.just(response.mutate().build());
            		                }
            		            });
    		 
    		 clientResponse.subscribe((response) -> {

    		        // here you can access headers and status code
    		        headers = response.headers();
    		        retreivedStatusCode = response.statusCode().value();

    		        Mono<String> bodyToMono = response.bodyToMono(String.class);
    		        // the second subscribe to access the body
    		        bodyToMono.subscribe((body) -> {

    		            // here you can access the body
    		            System.out.println("body:" + body);

    		            // and you can also access headers and status code if you need
    		            System.out.println("headers:" + headers.asHttpHeaders());
    		            System.out.println("statusCode:" + retreivedStatusCode);

    		        }, (ex) -> {
    		            // handle error
    		        });
    		    }, (ex) -> {
    		        // handle network error
    		    });
    		
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @Then("The response status code is {int}")
    public void thenStatusCodee(int expected) {
        Assertions.assertNotNull(clientResponse);
        Assertions.assertNotNull(responseCode);
        assertThat("status code is " + expected,
        		responseCode.value() == expected);
    }

    @Then("response status code is not present")
    public void thenStatusCodeeIsNotPresent() {
        Assertions.assertNull(lastResponse);
    }

//    @Then(" The returned string should be {string}")
//    public void thenStringIs(String expected) {
//        Assertions.assertEquals(expected, lastResponse.getBody());
//    }
    
//    @Then("The returned string should be: {string}")
//    public void thenStringIs(String expected) {
//    	 //String responseBody = response.block(); // block until the response is received
//
//        Assertions.assertEquals(expected, lastResponse.getBody());
//    }

    private List<Map<String, String>> ships;
    final RestTemplate restTemplate = new RestTemplate();

    @Given("We have gaffa taped the following spaceships together")
    public void weHaveGaffaTapedTheFollowingSpaceshipsTogether(DataTable shipsGaffaTaped) {
        List<Map<String, String>> maps = shipsGaffaTaped.asMaps();
        System.out.println(shipsGaffaTaped);
        System.out.println(maps);
        ships = maps;
    }


//    @When("send ships to rest controller")
//    public void sendShipsToRestController() {
//        String url = "http://localhost:" + port + "/ship/";
//        ships.forEach(ship -> {
//
//                    SpaceShipDto spaceShipDto = restTemplate.postForObject(
//                            url, ship, SpaceShipDto.class);
//                    System.out.println("Response from post " + spaceShipDto);
//                }
//        );
//    }
//
//    @Then("all ships should now have an id")
//    public void shipsShouldHaveIds(DataTable expectedShips) {
//        String url = "http://localhost:" + port + "/ship/";
//        SpaceShipDtoList shipsFromDb = restTemplate.getForObject(url, SpaceShipDtoList.class);
//        Objects.requireNonNull(shipsFromDb).getShips().forEach(shipFromDb -> Assertions.assertNotNull(shipFromDb.id()));
//        List<Map<String, String>> maps = expectedShips.asMaps();
//        for (Map<String, String> shiprow : maps) {
//            boolean foundByName = shipsFromDb.getShips().stream()
//                    .anyMatch(ship -> ship.shipName().equals(shiprow.get("shipName")));
//            Assertions.assertTrue(foundByName, shiprow.get("shipName") + " was not found in db");
//        }
//    }

}