package dtb.test;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import dtb.sample.service.Product;

public class ProductForm extends FormLayout {

	private static final long serialVersionUID = 364452418803255894L;
	
	Button save = new Button("Save", new SaveListener());
    Button cancel = new Button("Cancel", new CancelListener());
    TextField productId = new TextField("Product ID");
    TextField productName = new TextField("Product name");
    TextField category = new TextField("Category");
    TextField size = new TextField("Size");
    TextArea description = new TextArea("Description");

    Product product;

    BeanFieldGroup<Product> formFieldBindings;

    public ProductForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

		addComponents(actions, productId, productName, category, size, description);
    }

    private class SaveListener implements Button.ClickListener{

		private static final long serialVersionUID = 1054557642756277228L;

		@Override
		public void buttonClick(ClickEvent event) {
			save(event);
		}    	
    }
    

    public void save(Button.ClickEvent event) {
        try {
            formFieldBindings.commit();

            getUI().service.save(product);

            String msg = String.format("Saved '%s %s'.",
                    product.getProductId(),
                    product.getProductName());
            Notification.show(msg,Type.TRAY_NOTIFICATION);
            getUI().refreshProducts();
        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    private class CancelListener implements Button.ClickListener{

		private static final long serialVersionUID = 2279168311013055137L;

		@Override
		public void buttonClick(ClickEvent event) {
			cancel(event);
		}    	
    }
    
    public void cancel(Button.ClickEvent event) {
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        getUI().productList.select(null);
    }

    void edit(Product Product) {
        this.product = Product;
        if(Product != null) {
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(Product, this);
            productId.focus();
        }
        setVisible(Product != null);
    }

    @Override
    public MyPortletUI getUI() {
        return (MyPortletUI) super.getUI();
    }

}
