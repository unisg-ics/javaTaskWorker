import org.camunda.bpm.client.ExternalTaskClient;

import java.util.Collections;

public class JavaTaskWorker {

    public static void main (String... args) {

        String camunda_url = "https://siddhi25.bpmcep.ics.unisg.ch/engine-rest";

        // bootstrap the client
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl(camunda_url)
                .asyncResponseTimeout(1000)
                .build();

        // subscribe to topic (specified in process model)
        client.subscribe("charge-card")
                .handler((externalTask, externalTaskService) -> {

                    /** Put your business logic here**/
                    long amount = externalTask.getVariable("amount");
                    System.out.println("Deducted existing credit: " + amount);

                    externalTaskService.complete(externalTask);

        }).open();

    }

}