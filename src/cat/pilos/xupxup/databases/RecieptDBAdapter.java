package cat.pilos.xupxup.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecieptDBAdapter {

    private static final String TAG = "Reciept (DBAdapter) ";
    private static final String DATABASE_NAME = "Reciept";
    
    /* rowId field (necessary for all tables) */
    public static final String KEY_ROWID = "_id";
    
    /* Recipes Item Fields */
    public static final String KEY_TITLE = "title";
    public static final String KEY_SERVINGS= "servings";
    public static final String KEY_PREP_TIME = "prep_time";    
    public static final String KEY_DESC = "description";    
    public static final String KEY_LANG = "lang";    

    /* Step Fields */
    public static final String KEY_STEP_TEXT = "text";
    public static final String KEY_STEP_TYPE= "type";
    public static final String KEY_STEP_NUM = "number";    
    public static final String KEY_STEP_FK = "recieptId";    

    /* Recipe Ingredient */
    public static final String KEY_ING_NAME = "localName";
    public static final String KEY_ING_QUANT= "quantity";
    public static final String KEY_ING_UNIT = "unit";    
    public static final String KEY_ING_FK_RECIPE = "recieptId";      
    public static final String KEY_ING_FK_ING = "ingredientId";      
   
    /* Tables */
    private static final String DB_TABLE_RECIPE = "RecipeItem";
    private static final String DB_TABLE_RECIPE_INGREDIENT = "Ingredient";
    private static final String DB_TABLE_RECIPE_STEP = "Step";

    private static final int DATABASE_VERSION = 1;
    
 
    private static final String DB_CREATE_RECIPE_ITEM=
        "create table "+DB_TABLE_RECIPE+" ("+KEY_ROWID+" integer primary key autoincrement, "
        + KEY_TITLE+ " text not null, " 
        + KEY_DESC+ " text , " 
        + KEY_LANG+ " text , " 
        + KEY_SERVINGS+ " integer, " 
        + KEY_PREP_TIME+ " integer);";
 

    private static final String DB_CREATE_RECIPE_INGREDIENT =
        "create table "+DB_TABLE_RECIPE_INGREDIENT+" ("+KEY_ROWID+" integer primary key autoincrement, "
        + KEY_ING_FK_RECIPE+ " integer not null, " 
        + KEY_ING_NAME+ " text not null, " 
        + KEY_ING_QUANT+ " integer not null, " 
        + KEY_ING_UNIT+ " text, " 
        + KEY_ING_FK_ING+ " integer);";
 
    private static final String DB_CREATE_RECIPE_STEP =
         "create table "+DB_TABLE_RECIPE_STEP+" ("+KEY_ROWID+" integer primary key autoincrement, "
        + KEY_STEP_FK+ " integer not null, " 
   		+ KEY_STEP_TEXT+ " text not null, " 
        + KEY_STEP_TYPE+ " text, " 
        + KEY_STEP_NUM+ " integer not null);";
    
    
    private final Context context; 
 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
 
    
    
    public RecieptDBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
 
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DB_CREATE_RECIPE_ITEM);
            db.execSQL(DB_CREATE_RECIPE_INGREDIENT);
            db.execSQL(DB_CREATE_RECIPE_STEP);

        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    } 
    
  
    //---opens the database---
    public RecieptDBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
 
    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
 

    public long insertRecipe(String title, String desc, String lang, int servings, int prepTime) {
    	
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DESC, desc);
        initialValues.put(KEY_LANG, lang);
        initialValues.put(KEY_SERVINGS, servings);
        initialValues.put(KEY_PREP_TIME, prepTime);

        
        return db.insert(DB_TABLE_RECIPE, null, initialValues);
    }
    
    public long insertRecipeStep(long recipeId,String text, String type, int num) {
    	
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_STEP_TEXT, text);
        initialValues.put(KEY_STEP_FK, recipeId);
        initialValues.put(KEY_STEP_TYPE, type);
        initialValues.put(KEY_STEP_NUM, num);

        return db.insert(DB_TABLE_RECIPE_STEP, null, initialValues);
        
    }
    

	public long insertRecipeIng(int recipeId, String name, int quantity,String unit , long ingId) {

    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ING_FK_RECIPE,recipeId);
        initialValues.put(KEY_ING_QUANT,quantity);
        initialValues.put(KEY_ING_UNIT, unit);
        initialValues.put(KEY_ING_FK_ING,ingId);
        initialValues.put(KEY_ING_NAME,name);

        return db.insert(DB_TABLE_RECIPE_INGREDIENT, null, initialValues);
		
	}  
    
 
 

    public boolean deleteRecipe(long recieptId) {
    	
    	//Podríem intentar activar el el pragma --> delete on Cascade
    	
    	db.delete(DB_TABLE_RECIPE_STEP, KEY_STEP_FK + "=" + KEY_ROWID, null);
    	db.delete(DB_TABLE_RECIPE_INGREDIENT, KEY_ING_FK_RECIPE + "=" + KEY_ROWID, null);
        
        return db.delete(DB_TABLE_RECIPE, KEY_ROWID + "=" + KEY_ROWID, null) > 0;

    }
 

    public Cursor getAllReciepes(){
    	
        return db.query(DB_TABLE_RECIPE, new String[] {
        		KEY_ROWID, KEY_TITLE, KEY_DESC, KEY_LANG,KEY_SERVINGS,KEY_PREP_TIME}, 
                null, null, null, null, null);
    }
 

    public Cursor getRecipe(long recipeId) throws SQLException 
    {
        Cursor mCursor = db.query(DB_TABLE_RECIPE, new String[] {
                		KEY_ROWID, KEY_TITLE, KEY_DESC, KEY_LANG,KEY_SERVINGS,KEY_PREP_TIME},
                			KEY_ROWID + "=" + recipeId, 
                		null, null, null, null, null);
        
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        
        return mCursor;
    }
 
    public Cursor getRecipeSteps(long recipeId) throws SQLException {
    	
        Cursor mCursor = db.query(DB_TABLE_RECIPE_STEP, new String[] {
                		KEY_ROWID, KEY_STEP_FK, KEY_STEP_TEXT, KEY_STEP_TYPE,KEY_STEP_NUM},
                		KEY_STEP_FK + "=" + recipeId, 
                		null, null, null, null, null);
        
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        
        return mCursor;
    }
    
    public Cursor getRecipeIngredients(long recipeId) throws SQLException {
    	
        Cursor mCursor = db.query(DB_TABLE_RECIPE_INGREDIENT, new String[] {
                		KEY_ROWID,KEY_ING_NAME, KEY_ING_QUANT, KEY_ING_UNIT, KEY_ING_FK_RECIPE,KEY_ING_FK_ING},
                		KEY_STEP_FK + "=" + recipeId, 
                		null, null, null, null, null);
        
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        
        return mCursor;
        
    }

        
    
    /* At this time its not necessary to update recipes*/    
/*    public boolean updateTitle(long rowId, String isbn, 
    String title, String publisher) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ISBN, isbn);
        args.put(KEY_TITLE, title);
        args.put(KEY_PUBLISHER, publisher);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
  */

}