/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jungle.service;

import jungle.entity.Joueur;
import jungle.entity.Partie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public interface JoueurCrudService extends CrudRepository<Joueur, Long>{
    
    public Partie findOneById(long id);
    
}
