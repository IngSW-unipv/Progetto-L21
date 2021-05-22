package persistence;

public interface ModuleListener{

	public void dealWithModuleUpdate(Module module);
	public void dealWithSingularUpdate(String key, String value);
	
	
}
