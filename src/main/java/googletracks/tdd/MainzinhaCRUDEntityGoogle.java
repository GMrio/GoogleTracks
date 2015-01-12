package googletracks.tdd;

import googletracks.dao.GoogleDAO;
import googletracks.entities.EntityRetrieveIds;
import googletracks.entities.EntitysRetrieve;
import googletracks.model.Entity1;
import googletracks.model.TypeEntity;



public class MainzinhaCRUDEntityGoogle {

	public static void main(String[] args) throws Exception {
		
		try {
			GoogleDAO dao = new GoogleDAO();
			
			/**
			 * CADASTRAR
			 */
//			String idGoogle = dao.createEntity("24851569");
//			System.out.println(idGoogle);
			
//			Entity1 entity1 = new Entity1("9999999", TypeEntity.PERSON);
//			EntityRetriveIds ids = dao.createManyEntity(entity1);
//			System.out.println(ids.getEntityIds());
			
			/**
			 * REMOVER
			 */
			
//			boolean check = dao.removeEntity("1e36b3cde0066f69");
//			System.out.println(check);
			
			/**
			 * LISTAR
			 */
			
			//EntitysRetrive reEntitysRetrive = dao.findAllByEntityId("984769073");
			//EntitysRetrive reEntitysRetrive = dao.findAllByEmpty();
			//EntitysRetrive reEntitysRetrive = dao.findAllByMindId("1e36b3cde0066f69");
			//System.out.println(reEntitysRetrive.getEntities());
		} catch (Exception e) {
			System.out.println("ERRO");
		}
		
	}
	
}
