
package services;

import models.Entry;
import dao.EntryDAO;
import play.Logger;

public class EntryDAOService implements DAOService {

  final Logger.ALogger logger = Logger.of(this.getClass());

  EntryDAO dao;

  /**
    * create new Entry
    * Receive title and content form Form 
    * @param description: description of new task
    */ 
  public void createEntryFromForm() {
      
    logger.debug("generate Entry from newentry Post-Form");

    //Bind data directly from HTTP Request and create new Entry-Object
    //Entry e = entryForm.bindFromRequest().get();

    logger.debug("delegation to EntryDAO"); 

    this.dao = new EntryDAO();
    //this.dao.create(e);
     
  }

}