package com.backend.Controllers;

import com.backend.Models.PersonEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonRepo extends GenericRepo<PersonEntity, Long>
{

}
