package com.appstra.users.implementation;

import com.appstra.users.dto.MassiveUsersDTO;
import com.appstra.users.entity.Person;
import com.appstra.users.entity.User;
import com.appstra.users.repository.UserRepository;
import com.appstra.users.service.PersonService;
import com.appstra.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PersonService personService;

    public UserImpl(UserRepository userRepository, PersonService personService) {
        this.userRepository = userRepository;
        this.personService = personService;
    }

    @Override
    public User saveUser(User user) {
        Optional<User> optionalUser = userRepository.findByUserUser(user.getUserUser());
        if (optionalUser.isEmpty()) {
            if (!isValidPassword(user.getUserPassword())) {
                throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula, un número, un carácter especial y un mínimo de 5 caracteres.");
            }
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            user.setUserCreationDate(Timestamp.valueOf(LocalDateTime.now()));
            user.setUserEditDate(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.save(user);
        }else {
            throw new IllegalArgumentException("ERRO: este usuario ya existe " + user.getUserUser());
        }
    }

    @Override
    public User upDateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe: " + user.getUserId()));

        if (!isValidPassword(user.getUserPassword())) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula, un número, un carácter especial y un mínimo de 5 caracteres.");
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserCreationDate(existingUser.getUserCreationDate()); // Retain original creation date
        user.setUserEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public List<MassiveUsersDTO> uploadMassiveUsers(MultipartFile document) {
        List<MassiveUsersDTO> usersList = new ArrayList<>();

        try (InputStream inputStream = document.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Lee la primera hoja
            for (Row row : sheet) {
                MassiveUsersDTO userDto = new MassiveUsersDTO();
                // Omite la fila de encabezado si es necesario
                if (row.getRowNum() == 0) continue;

                Cell userCell = row.getCell(0); // Primera columna
                Cell Identification = row.getCell(1); // Segunda columna

                if (userCell != null && Identification != null) {
                    String user = userCell.getStringCellValue();
                    Integer personNumberIdentification = (int)Identification.getNumericCellValue();

                    User newUser = new User(user,"AppSt@r2024*",1,1,11);
                    try {
                        //Busca a la persona a quien se le va a signar
                        Person person = this.personService.getPersonPersonNumberIdentification(personNumberIdentification);
                        if(person != null){
                            // guarda el nuevo usuario
                            User createUser = this.saveUser(newUser);
                            if(person.getUser() == null){
                                //le asigna el usuario
                                person.setUser(createUser);
                                //actualiza la persona con el usuario
                                this.personService.upDatePerson(person);

                                userDto.setUserId(createUser.getUserId());
                                userDto.setUserName(user);
                                userDto.setState("Creado correctamente");
                            }else{
                                throw new IllegalArgumentException("ERRO: la persona con el numero de identificacion " + personNumberIdentification + " Ya tinene usuario:" + person.getUser().getUserUser());
                            }
                        }else{
                            throw new IllegalArgumentException("ERRO: la persona con el numero de identificacion " + personNumberIdentification + " No existe");
                        }
                    } catch (IllegalArgumentException e) {
                        userDto.setUserName(user);
                        userDto.setState(e.getMessage()); // Captura el mensaje de la excepción
                    }
                    usersList.add(userDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar el archivo Excel", e);
        }

        return usersList;
    }

    @Override
    public Person createUserpersonId(Integer personId) {
        Person person = this.personService.getPerson(personId);

        String[] firstNameParts = person.getPersonFirstName().split(" ");
        String[] lastNameParts  = person.getPersonLastName().split(" ");

        String firstPart = firstNameParts.length > 0 ? firstNameParts[0] : "";
        String lastPart = lastNameParts.length > 0 ? lastNameParts[0] : "";

        String userUser = firstPart +"."+ lastPart;

        User newUser = new User(userUser,"AppSt@r2024*",1,1,11);

        newUser = this.saveUser(newUser);

        if(newUser.getUserId() != null){
            person.setUser(newUser);
            person = this.personService.upDatePerson(person);
        }
        return person;
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }
}
