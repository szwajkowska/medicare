
[![Build Status](https://travis-ci.org/szwajkowska/medicare.svg?branch=test)](https://travis-ci.org/szwajkowska/medicare)

# Medicare
Repository contains project, which is a simple medical portal.
Application allows to choose specialization, doctor and date of visit. User can log in, make appointments and cancel them.
There is also a page, where admin can add and delete visits to the selected specialization and doctor. 
To store data I have used MySQL database.

<b>Code example</b>

    @PostMapping
    String addDoctor(@ModelAttribute DoctorModel doctorModel, ModelMap modelMap) {
        doctorService.addDoctor(doctorModel.getFirstName(), doctorModel.getLastName(),
                doctorModel.getSpecializationId());
        modelMap.put("specializations", specializationList.findAllSpecializations());
        modelMap.put("firstName", doctorModel.getFirstName());
        modelMap.put("lastName", doctorModel.getLastName());
        modelMap.put("specializationName",
                specializationList.findById(doctorModel.getSpecializationId()).getSpecializationName());
        return "admin_doctor";
    }

## Deployment

To deploy project I have used Heroku. As it is a free platform, page loading can last a bit longer. 
To log in you can sign in and create new user or use an account: 
* login: user1
* password: user1


https://whispering-wave-19025.herokuapp.com

## Tests

Project contains JUnit tests, which are run at each commit using Travis CI. They are testing each controller and servise methods.
To execute tests run command:

    ./gradlew clean test

<b>Controller test example</b>


    @Test
    public void shouldReturnDoctorsBySpecializationId() throws Exception{
        Specialization specialization = new Specialization("1", "chirurgia");
        specializationRepository.save(specialization);
        Doctor doctor1 = new Doctor("Jan", "Kowalski", "2", Arrays.asList(specialization));
        Doctor doctor2 = new Doctor("Adam", "Nowak", "3", Arrays.asList(specialization));
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/doctors?specializationId=1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("[{'id':'2','firstName':'Jan','lastName':'Kowalski'}," +
                               "{'id':'3','firstName':'Adam','lastName':'Nowak'}]"));
    }
    

    


