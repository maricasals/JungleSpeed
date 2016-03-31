/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jungle.controller;

import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import jungle.entity.EtatPartieEnum;
import jungle.entity.Joueur;
import jungle.entity.Partie;
import jungle.service.JoueurCrudService;
import jungle.service.PartieCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author admin
 */
@Controller
public class PartieController {

    @Autowired
    PartieCrudService partieCrudService;

    @Autowired
    JoueurCrudService joueurCrudService;

    @RequestMapping(value = "new_joueur/{idPartie}", method = RequestMethod.GET)
    private String newJouer(Model model, @PathVariable(value = "idPartie") long id) {
        model.addAttribute("newJoueur", new Joueur());
        Partie laPartie = partieCrudService.findOneById(id);
        model.addAttribute("partie", laPartie);
        return "/partie/formInscription";
    }

    @RequestMapping(value = "rejoindre/{idPartie}", method = RequestMethod.POST)
    private String rejoindrePartie(@PathVariable(value = "idPartie") long id, @ModelAttribute(value = "newJoueur") Joueur j, HttpServletRequest req, Model model) {

        req.getSession().setAttribute("nom", j.getNom());

        //Voir combien de jours il y a dans cette partie
        Partie laPartie = partieCrudService.findOneById(id);
        int joueurs = laPartie.getNmbJoueurs();
        
        //Ajouter joueur à la partie
        
        j.setNumCarts(20);
        j.getParties().add(laPartie);
        joueurCrudService.save(j);
        
        laPartie.getJoueurs().add(j);
        laPartie.setNmbJoueurs(joueurs + 1);

        if (joueurs == 2) {
            
            laPartie.setEtat(EtatPartieEnum.ENCOURS); //Si 2 joueurs fermer partie
            return ("Partie fermée");
        }

        return "/partie/tablier";
    }

    @RequestMapping(value = "lister", method = RequestMethod.GET)
    private String listerPartie(Model model) {
        Iterable<Partie> partiesDispo = partieCrudService.findByEtat(EtatPartieEnum.DISPONIBLE);
        model.addAttribute("PartiesDispo", partiesDispo);
        return "/partie/formLister";
    }

    @Scheduled(fixedDelay = 30000)//Toute les 0,5 minutes
    public void creationPartie() {
        //Nmbr MAX de parties
        int nmbMaxParties = 4;
        //Calcul des parties DISPONIBLES
        int nmbPartiesDispos = partieCrudService.findByEtat(EtatPartieEnum.DISPONIBLE).size();

        //Si nmbPartiesDispos < nmbMaxParties --> Creation de nouvelle parties
        if (nmbPartiesDispos < nmbMaxParties) {
            for (int i = nmbPartiesDispos; i < nmbMaxParties; i++) {
                partieCrudService.save(new Partie(generateString(5)));
            }
        }
    }

    //Generer un nom aleatoire pour nomé la Partie de façon automatic
    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static int charLength = chars.length();

    public static String generateString(int length) {
        StringBuilder pass = new StringBuilder(charLength);
        for (int x = 0; x < length; x++) {
            int i = (int) (Math.random() * charLength);
            pass.append(chars.charAt(i));
        }
        return pass.toString();
    }
}
