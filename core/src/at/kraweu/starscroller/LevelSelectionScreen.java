package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Kraweu on 30.03.2016.
 */
public class LevelSelectionScreen implements Screen
{
    private Image background;
    private Stage stage;
    private Skin skin;
    private Group ui;
    private Starscroller game;

    public LevelSelectionScreen(Starscroller game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        skin = new Skin();
        skin.addRegions(new TextureAtlas("packedImgs/Starscroller.pack"));
        skin.addRegions(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));
        Table levelTable = new Table();

        ScrollPane scrollPane = new ScrollPane(levelTable);
        scrollPane.setFadeScrollBars(true);
        scrollPane.setOverscroll(false, true);

//        background = new Image(skin, "Backgrounds/SpaceBackground3cut");
//        stage.addActor(background);
//        background = new Background();
//        background.setAsset("Backgrounds/SpaceBackground3cut",0f);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.add(label("Levels", Color.NAVY)).pad(120).top();
        mainTable.row();
        mainTable.add(scrollPane);
        ui = new Group();
        ui.addActor(mainTable);
//        stage.addActor(background);
        stage.addActor(ui);
        Gdx.input.setInputProcessor(stage);


        levelTable.defaults().pad(30);
        levelTable.center();
        levelTable.top().padTop(50);
        MenuButton[] levelbuttons = new MenuButton[game.levels.length];
        for (int i = 0; i < game.levels.length; i++)
        {
            levelbuttons[i] = new MenuButton(game.levels[i].name, skin)
            {
                @Override
                public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
                {
                    try
                    {
                        game.setScreen(new GameScreen(game, game.player, game.levels[Integer.parseInt(actor.getName())]));
                    } catch (NumberFormatException e)
                    {
                        System.out.println("LevelButton not named Properly: " + actor.getName());
                        System.out.println("Level could not be Started");
                    }
                }
            };
            levelbuttons[i].getbutton().setName("" + i);
            levelTable.add(levelbuttons[i].getbutton());
            levelTable.row();
        }
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        levelTable.add(label("Test", Color.GRAY)).pad(50);
        levelTable.row();
        MenuButton buttonBack = new MenuButton("Back", skin, "Go Back to the Main Menu")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                game.setScreen(new StartMenuScreen(game));
            }
        };
        levelTable.add(buttonBack.getbutton());
        levelTable.row();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        ui.setSize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }

    /**
     * used to tidy up the label adding a bit
     */
    private Label label(String text, Color color)
    {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center, Align.center);
        label.setColor(color);
        return label;
    }
}
