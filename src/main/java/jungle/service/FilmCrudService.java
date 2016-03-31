/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jungle.service;

import org.springframework.data.repository.CrudRepository;
import jungle.entity.Film;

/**
 *
 * @author tom
 */
public interface FilmCrudService extends CrudRepository<Film, Long>{
    
}
