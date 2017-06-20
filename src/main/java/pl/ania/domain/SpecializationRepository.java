package pl.ania.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

interface SpecializationRepository extends MongoRepository<Specialization, String> {

    String findBySpecialization(String specialization);
}
