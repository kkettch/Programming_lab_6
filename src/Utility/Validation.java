package Utility;

import MusicBand.Coordinates;
import MusicBand.Person;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Validation {
    private static final List<Integer> listOfIdValidation = new ArrayList<>();
    private static final List<String> listOfPassportIDValidation = new ArrayList<>();
    public boolean validateID(Integer id) {
        if (listOfIdValidation.contains(id)) {
            return false;
        } else {
            listOfIdValidation.add(id);
            return id != null && id > 0;
        }
    }
    public boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }
    public boolean validateX(int x) {
        return x <= 223;
    }
    public boolean validateY(int y) {
        return y >= -325;
    }
    public boolean validateCoordinates(Coordinates coordinates) {
        return coordinates != null && validateX(coordinates.getX()) && validateY(coordinates.getY());
    }
    public boolean validateCreationDate(LocalDateTime creationDate) {
        return creationDate != null;
    }
    public boolean validateNumberOfParticipants(int numberOfParticipants) {
        return numberOfParticipants > 0;
    }
    public boolean validateAlbumsCount(Integer albumsCount) {
        if (albumsCount == null) {
            return true;
        } else {
            return albumsCount > 0;
        }
    }
    public boolean validatePersonName(String personName) {
        return personName != null && !personName.isEmpty();
    }
    public boolean validatePersonBirthday(Date personBirthday) {
        return personBirthday != null;
    }
    public boolean validatePersonHeight(Double height) {
        return (height != null && height > 0);
    }
    public boolean validatePersonWeight(float weight) {
        return weight > 0;
    }
    public boolean validatePersonPassportID(String passportID) {
        if (passportID == null) {
            return true;
        } else if (listOfPassportIDValidation.contains(passportID)) {
            return false;
        } else {
            listOfPassportIDValidation.add(passportID);
            return passportID.length() <= 21;
        }
    }
    public boolean validatePerson(Person person) {
        return validatePersonName(person.getPersonName()) &&
                validatePersonBirthday(person.getPersonBirthday()) &&
                validatePersonHeight(person.getPersonHeight()) &&
                validatePersonWeight(person.getPersonWeight()) &&
                validatePersonPassportID(person.getPersonPassportID());
    }
}