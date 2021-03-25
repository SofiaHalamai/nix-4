package ua.com.alevel.secondLevel.stringValidation;

import static ua.com.alevel.Main.scan;

public class IntroducingStringToBeChecked {

    public void stringInputAndValidation (){
        System.out.print("Enter the string for validation: ");
        String inputString = scan.next();
        AdmissibilityCheck admissibilityCheck = new AdmissibilityCheck();
        System.out.println(admissibilityCheck.checkOfBracket(inputString));
    }
}
