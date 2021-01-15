package tasks;

import com.google.inject.AbstractModule;


/**
* module which allows to run command in startup of Play Framework
*/
public class InitTasksModule extends AbstractModule {

	/**
	* this function gets called when this class is loaded
	*/
  	@Override
  	protected void configure() {
    	bind(InitActorTask.class).asEagerSingleton();
  	}
  	
}