package common.Utility;
import common.Exceptions.*;
import common.MusicBand.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * Класс для запроса валидных полей у пользователя при выполнении команды add
 * @author maria
 */
public class CreatorOfMusicBand {
    private final ConsoleManager consoleManager = new ConsoleManager();
    private CollectionManager collectionManager;
    public CreatorOfMusicBand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public CreatorOfMusicBand(){}
    public Integer assignID() {
        Integer id;
//        do {
        Random random = new Random();
        id = random.nextInt(100000) + 1;
//        } while (collectionManager.checkUniqueId(id));
        return id;
    }
    public String assignName() {
        String name;
        while (true) {
            try {
                consoleManager.print("Введите название музыкальной группы: ");
                name = consoleManager.getString().trim();
                if(name.isEmpty()) throw new MustNotBeEmptyException();
                break;
            }catch(MustNotBeEmptyException ex){
                consoleManager.println("Название музыкальной группы не может принимать значение null!");
            }
        }
        return name;
    }
    public int assignX() {
        String stringX;
        int x;
        while (true) {
            try{
                consoleManager.print("Введите значение координаты (int) x, меньшее 224: ");
                stringX = consoleManager.getString().trim();
                if (stringX.isEmpty()) throw new MustNotBeEmptyException();
                x = Integer.parseInt(stringX);
                int max_X = 223;
                if (x > max_X) throw new WrongFieldLimitException();
                break;
            } catch (MustNotBeEmptyException msg) {
                consoleManager.println("Координата х не может принимать значение null!");
            } catch (WrongFieldLimitException msg) {
                consoleManager.println("Координата x должна быть меньше 224!");
            } catch (NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Координата х должна иметь тип данных int!");
            }
        }
        return x;
    }
    public int assignY() {
        String stringY;
        int y;
        while (true) {
            try{
                consoleManager.print("Введите значение координаты (int) y, большее -326: ");
                stringY = consoleManager.getString().trim();
                if (stringY.isEmpty()) throw new MustNotBeEmptyException();
                y = Integer.parseInt(stringY);
                int min_Y = -325;
                if (y < min_Y) throw new WrongFieldLimitException();
                break;
            } catch (MustNotBeEmptyException msg) {
                consoleManager.println("Координата y не может принимать значение null!");
            } catch (WrongFieldLimitException msg) {
                consoleManager.println("Координата y должна быть больше -326!");
            } catch (NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Координата y должна иметь тип данных int!");
            }
        }
        return y;
    }
    public Coordinates assignCoordinates() throws IllegalValuesException {
        int x = assignX();
        int y = assignY();
        return new Coordinates(x, y);
    }
    public int assignNumberOfParticipants() {
        String stringNumberOfParticipants;
        int numberOfParticipants;
        while(true) {
            try{
                consoleManager.print("Введите значение (int) количества участников группы, большее нуля: ");
                stringNumberOfParticipants = consoleManager.getString().trim();
                if (stringNumberOfParticipants.isEmpty()) throw new MustNotBeEmptyException();
                numberOfParticipants = Integer.parseInt(stringNumberOfParticipants);
                if (numberOfParticipants <= 0) throw new WrongFieldLimitException();
                break;
            } catch (MustNotBeEmptyException msg) {
                consoleManager.println("Количество участников группы не может принимать значение null!");
            } catch (WrongFieldLimitException msg) {
                consoleManager.println("Значение количества участников должно быть больше 0!");
            } catch(NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Количество участников должно иметь тип данных int!");
            }
        }
        return numberOfParticipants;
    }
    public Integer assignAlbumsCount() {
        String stringAlbumsCount;
        Integer albumsCount;
        while(true) {
            try{
                consoleManager.print("Введите значение (int) количества музыкальных альбомов группы, большее нуля (опционально): ");
                stringAlbumsCount = consoleManager.getString().trim();
                if (stringAlbumsCount.isEmpty()) {
                    albumsCount = null;
                } else {
                    albumsCount = Integer.parseInt(stringAlbumsCount);
                    if (albumsCount <= 0) throw new WrongFieldLimitException();
                }
                break;
            } catch (WrongFieldLimitException msg) {
                consoleManager.println("Значение количества музыкальных альбомов группы должно быть больше 0!");
            } catch(NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Количество альбомов должно иметь тип данных int!");
            }
        }
        return albumsCount;
    }
    public String assignDescription() {
        String description;
        consoleManager.print("Введите описание группы (опционально): ");
        description = consoleManager.getString().trim();
        if (description.isEmpty()) {
            description = null;
        }
        return description;
    }
    public MusicGenre assignMusicGenre() {
        String stringMusicGenre;
        MusicGenre musicGenre;
        while(true){
            consoleManager.println("Список доступных музыкальных жанров: ");
            consoleManager.println(MusicGenre.nameList());
            consoleManager.print("Введите музыкальный жанр группы (опционально): ");
            stringMusicGenre = consoleManager.getString().trim().toUpperCase();
            if (stringMusicGenre.isEmpty()){
                musicGenre = null;
                break;
            } else {
                if (MusicGenre.nameList().contains(stringMusicGenre)) {
                    musicGenre = MusicGenre.valueOf(stringMusicGenre);
                    break;
                } else {
                    consoleManager.println("В списке нет указанного жанра. Попробуйте снова!");
                }
            }
        }
        return musicGenre;
    }
    public String assignPersonName() {
        String personName;
        while(true) {
            try {
                consoleManager.print("Введите имя солиста группы: ");
                personName = consoleManager.getString().trim();
                if(personName.isEmpty()) throw new MustNotBeEmptyException();
                break;
            }catch(MustNotBeEmptyException msg){
                consoleManager.println("Имя солиста группы не может принимать значение null!");
            }
        }
        return personName;
    }
    public Date assignPersonBirthday() {
        Date date;
        String personBirthday;
        while(true) {
            consoleManager.print("Введите дату рождения солиста (формат: yyyy-MM-dd): ");
            try{
                personBirthday = consoleManager.getString().trim();
                String[] personBirthdayArray = personBirthday.split("-");
                int year = Integer.parseInt(personBirthdayArray[0]);
                int month = Integer.parseInt(personBirthdayArray[1]);
                int day = Integer.parseInt(personBirthdayArray[2]);
                if (personBirthdayArray[0].length() < 4 || year > 2023) {
                    consoleManager.println("Год указан неверно!");
                } else if (personBirthdayArray[1].length() < 2 || month < 1 || month > 12) {
                    consoleManager.println("Месяц указан неверно!");
                } else if (personBirthdayArray[2].length() < 2 || day > 31 || day < 1) {
                    consoleManager.println("День указан неверно");
                } else {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = dateFormat.parse(personBirthday);
                    break;
                }
            } catch (NumberFormatException msg) {
                consoleManager.println("Укажите дату в верном формате! Верный формат: yyyy-MM-dd");
            } catch (ParseException msg) {
                consoleManager.println("Введенная дата не соответствует требуемому формату! Верный формат: yyyy-MM-dd");
            }
        }
        return date;
    }
    public Double assignPersonHeight() {
        String stringPersonHeight;
        double personHeight;
        while(true){
            try{
                consoleManager.print("Введите (Double) рост солиста: ");
                stringPersonHeight = consoleManager.getString().trim();
                if (stringPersonHeight.isEmpty()) throw new MustNotBeEmptyException();
                personHeight = Double.parseDouble(stringPersonHeight.replace(",", "."));
                if (personHeight <= 0) throw new WrongFieldLimitException();
                break;
            }catch (NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Рост солиста должен иметь тип данных Double!");
            }catch (MustNotBeEmptyException msg) {
                consoleManager.println("Рост солиста не может принимать значение null!");
            }catch (WrongFieldLimitException msg) {
                consoleManager.println("Рост солиста должен быть больше нуля!");
            }
        }
        return personHeight;
    }
    public float assignPersonWeight() {
        String stringPersonWeight;
        float personWeight;
        while(true){
            try{
                consoleManager.print("Введите (float) вес солиста: ");
                stringPersonWeight = consoleManager.getString().trim();
                if (stringPersonWeight.isEmpty()) throw new MustNotBeEmptyException();
                personWeight = Float.parseFloat(stringPersonWeight.replace(",", "."));
                if (personWeight <= 0) throw new WrongFieldLimitException();
                break;
            }catch (NumberFormatException msg) {
                consoleManager.println("Введено число неверного формата! Рост солиста должен иметь тип данных float!");
            }catch (MustNotBeEmptyException msg) {
                consoleManager.println("Вес солиста не может принимать значение null!");
            }catch (WrongFieldLimitException msg) {
                consoleManager.println("Вес солиста должен быть больше нуля!");
            }
        }
        return personWeight;
    }
    public String assignPersonPassportID() {
        String passportID;
        while(true) {
            try{
                consoleManager.print("Введите passportID солиста группы, длинной не более 21 символа (опционально): ");
                passportID = consoleManager.getString().trim();
                if (passportID.isEmpty()) {
                    passportID = null;
                    break;
                } else {
                    if (passportID.length() > 21) throw new WrongFieldLimitException();
//                    if (collectionManager.checkUniquePassportID(passportID)) throw new FieldMustBeUniqueException();
                    break;
                }
            }catch (WrongFieldLimitException msg) {
                consoleManager.println("Длина поля passportID не должна быть больше 21 символа!");
//            }catch (FieldMustBeUniqueException msg) {
//                consoleManager.println("Значение PassportID должно быть уникальным!");
//            }
            }
        }
        return passportID;
    }
    public Person assignPerson(String passport) throws IllegalValuesException {
        String name = assignPersonName();
        Date birthday = assignPersonBirthday();
        Double height = assignPersonHeight();
        float weight = assignPersonWeight();
        return new Person(name, birthday, height, weight, passport);
    }

    /**
     * Установление всех полей в объект класса common.MusicBand
     * @param musicBand объекта класса common.MusicBand
     */
//    public void setMusicBandByUser(MusicBand musicBand) {
//        try {
//            musicBand.setId(assignID());
//            setMusicBand(musicBand);
//        } catch (IllegalValuesException e) {
//            consoleManager.println(e.getMessage());
//        }
//    }

    /**
     * Установление всех полей в объекта класса common.MusicBand без изменения имеющегося id
     * @param musicBand
     */
    public void setMusicBandForUpdateIdCommand(MusicBand musicBand, Integer numberInCollection) {
        MusicBand newmusicBand = collectionManager.getMusicBandVector().get(numberInCollection);
        try {
            newmusicBand.setName(musicBand.getName());
            newmusicBand.setCoordinates(musicBand.getCoordinates());
            newmusicBand.setCreationDate(musicBand.getCreationDate());
            newmusicBand.setNumberOfParticipants(musicBand.getNumberOfParticipants());
            newmusicBand.setAlbumsCount(musicBand.getAlbumsCount());
            newmusicBand.setDescription(musicBand.getDescription());
            newmusicBand.setGenre(musicBand.getGenre());
            newmusicBand.setFrontMan(musicBand.getFrontMan());
        } catch (IllegalValuesException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMusicBand(MusicBand musicBand) {
        try {
            musicBand.setName(assignName());
            musicBand.setCoordinates(assignCoordinates());
            musicBand.setCreationDate(LocalDateTime.now());
            musicBand.setNumberOfParticipants(assignNumberOfParticipants());
            musicBand.setAlbumsCount(assignAlbumsCount());
            musicBand.setDescription(assignDescription());
            musicBand.setGenre(assignMusicGenre());
        } catch (IllegalValuesException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMusicBandOnClient(MusicBand musicBand, String passportId) {
        try {
            setMusicBand(musicBand);
            musicBand.setFrontMan(assignPerson(passportId));
        }catch (IllegalValuesException e) {
            throw new RuntimeException(e);
        }
    }
}