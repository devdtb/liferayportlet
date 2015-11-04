package dtb.test;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import dtb.sample.service.Product;
import dtb.sample.service.ProductService;

@Theme("valo")
@SuppressWarnings("serial")
@Widgetset("dtb.test.AppWidgetSet")
public class MyPortletUI extends UI {

	Label label = new Label("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
			+ "Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. "
			+ "Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. "
			+ "Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. "
			+ "Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad "
			+ "litora torquent per conubia nostra, per inceptos himenaeos.</p>"
			+ "<p><a href=\"https://github.com/devdtb/vapo\" target=\"_blank\">GitHub</a></p>"
			+ "<hr>", ContentMode.HTML);
	TextField filter = new TextField();
    Grid productList = new Grid();
    Button newProduct = new Button("New Product");

    ProductForm productForm = new ProductForm();

    ProductService service = ProductService.createDemoService();

    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        setContent(getLayout());
    }


    private void configureComponents() {
        newProduct.addClickListener(new EditListener());

        filter.setInputPrompt("Filter Products...");
        filter.addTextChangeListener(new TextChangeListener());
        
        productList.setContainerDataSource(new BeanItemContainer<>(Product.class));
        productList.setColumnOrder("productId", "productName", "category", "size", "description");
        productList.removeColumn("id");
        productList.setSelectionMode(Grid.SelectionMode.SINGLE);
        productList.addSelectionListener(new SelectionListener());
        
        refreshProducts();
    }

    private class EditListener implements Button.ClickListener{

		private static final long serialVersionUID = -520939208716843096L;

		@Override
		public void buttonClick(ClickEvent event) {
			productForm.edit(new Product());
		}    	
    }
    
    private class TextChangeListener implements FieldEvents.TextChangeListener{

		private static final long serialVersionUID = -7936136643675154139L;

		@Override
		public void textChange(TextChangeEvent event) {
			refreshProducts(event.getText());			
		}
    }

    private class SelectionListener implements SelectionEvent.SelectionListener{

		private static final long serialVersionUID = 9110805743717948810L;

		@Override
		public void select(SelectionEvent event) {
			productForm.edit((Product) productList.getSelectedRow());
		}
    }    
    
    private Layout getLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, newProduct);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, productList);
        left.setSizeFull();
        productList.setSizeFull();
        left.setExpandRatio(productList, 1);

        HorizontalLayout toolLayout = new HorizontalLayout(left, productForm);
        toolLayout.setSizeFull();
        toolLayout.setExpandRatio(left, 1);

        VerticalLayout mainLayout = new VerticalLayout(label, toolLayout);
        mainLayout.setExpandRatio(toolLayout, 1);
        
        return mainLayout;
    }

    void refreshProducts() {
        refreshProducts(filter.getValue());
    }

    private void refreshProducts(String stringFilter) {
        productList.setContainerDataSource(new BeanItemContainer<>(
                Product.class, service.findAll(stringFilter)));
        productForm.setVisible(false);
    }
}
