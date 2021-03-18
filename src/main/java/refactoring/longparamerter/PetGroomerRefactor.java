package refactoring.longparamerter;

import refactoring.Animal;

public class PetGroomerRefactor {

    public Animal groom(Animal pet, GroomRequest groomRequest) {

        groomThePet(pet, groomRequest);
        handleCallClientCallBack(pet, groomRequest);

        return pet;
    }

    private void groomThePet(Animal pet, GroomRequest groomRequest) {
        System.out.println(groomRequest.getGroomerName() + " will " +
                groomRequest.getServiceRequest() + " to " +
                pet.getName() + " for "
                + groomRequest.getPrice() + " dollars.");
    }

    private void handleCallClientCallBack(Animal pet, GroomRequest groomRequest) {
        if (groomRequest.getCallWhenReady()) {
            tellClientThatWeWillCallBack(pet, groomRequest);
        } else {
            tellClientWeWontCallBack();
        }
    }

    private void tellClientThatWeWillCallBack(Animal pet, GroomRequest groomRequest) {
        System.out.println(groomRequest.getGroomerName() + " will call you when " + pet.getName() + " is ready");
    }

    private void tellClientWeWontCallBack() {
        System.out.println("We won't call you");
    }

}
