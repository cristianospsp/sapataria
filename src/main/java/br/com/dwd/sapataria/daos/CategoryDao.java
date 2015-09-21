package br.com.dwd.sapataria.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;

import br.com.dwd.sapataria.models.Category;

public class CategoryDao
{

   @Inject
   private EntityManager manager;

   public List<Category> all()
   {
      return manager.createQuery("select c from Category c", Category.class).getResultList();
   }

   public void save(Category category)
   {
      manager.persist(category);
   }

   public Category findById(Integer id)
   {
      return manager.find(Category.class, id);
   }

   public void remove(Category category)
   {
      manager.remove(category);
   }

   public void update(Category category)
   {
      manager.merge(category);
   }

}
