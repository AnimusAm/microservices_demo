package hr.smilebacksmile.controller;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import hr.smilebacksmile.dao.TestDataRepository;
import hr.smilebacksmile.domain.TestData;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class TestDataEditor extends VerticalLayout implements KeyNotifier {

    private final TestDataRepository repository;

    /**
     * The data that we are currently working with
     */
    private TestData data;

    /* Fields to edit properties in TestData entity */
    TextField code = new TextField("Code");
    TextField name = new TextField("Name");

    /* Action buttons */
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<TestData> binder = new Binder<>(TestData.class);
    private ChangeHandler changeHandler;

    @Autowired
    public TestDataEditor(TestDataRepository repository) {
        this.repository = repository;

        add(code, name, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editData(data));
        setVisible(false);
    }

    void delete() {
        repository.delete(data);
        changeHandler.onChange();
    }

    void save() {
        repository.save(data);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editData(TestData d) {
        if (d == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = d.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            data = repository.findById(d.getId()).get();
        }
        else {
            data = d;
        }
        cancel.setVisible(persisted);

        // Bind data properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(data);

        setVisible(true);

        // Focus first name initially
        code.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
