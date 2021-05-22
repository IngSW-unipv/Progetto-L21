package persistence;

public interface ModuleListener{

	public void dealWithModuleUpdate(Module moduleThatGotUpdated);
	public void dealWithSingularUpdate(String key, String value);
	
	
}
