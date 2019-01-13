package hr.smilebacksmile.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import hr.smilebacksmile.dao.TestDataRepository;
import hr.smilebacksmile.dao.TestDataSpecificationBuilder;
import hr.smilebacksmile.domain.TestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route
public class TestDataView extends VerticalLayout {

    private final TestDataRepository repo;
    private final TestDataEditor editor;
    final Grid<TestData> grid;
    private final TextField filter;
    private final Button addNewBtn;

    public TestDataView(TestDataRepository repo, TestDataEditor editor) {

        // define components
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(TestData.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New data", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);
        grid.setHeight("300px");
        grid.setColumns("id", "code", "name");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        filter.setPlaceholder("Filter by not null");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        filter.addValueChangeListener(e -> listTestData(e.getValue()));

        // Connect selected Data to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editData(e.getValue());
        });

        // Instantiate and edit new TestData when the new button is clicked
        addNewBtn.addClickListener(e -> editor.editData(new TestData("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listTestData(filter.getValue());
        });

        // Initialize listing
        listTestData(null);
    }

    private void listTestData() {
        grid.setItems(StreamSupport.stream(repo.findAll().spliterator(), true).collect(Collectors.toList()));
    }

    private void listTestData(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            listTestData();
        }
        else {

            TestDataSpecificationBuilder builder = new TestDataSpecificationBuilder();
            Pattern pattern = Pattern.compile("\\s*(\\w+?),\\s*");
            Matcher matcher = pattern.matcher(filterText + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), "NOT NULL", null);
            }

            Specification<TestData> spec = builder.build();
            grid.setItems(StreamSupport.stream(repo.findAll(spec).spliterator(), true).collect(Collectors.toList()));

        }
    }
}
