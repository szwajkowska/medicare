package pl.ania.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

interface SpecializationRepository extends JpaRepository<Specialization, String> {



}
