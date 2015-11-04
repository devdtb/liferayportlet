package dtb.sample.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;


public class ProductService implements Serializable{

	private static final long serialVersionUID = 2066145732429598161L;

	private  static Map<String, String> gases = new HashMap<String, String>(){
		private static final long serialVersionUID = -2262727187814197417L;
	{
		put("Azetylen", "Technische Gase");
		put("Distickstoffoxid", "Technische Gase");
		put("Kohlendioxid", "Technische Gase");
		put("Pressluft", "Technische Gase");
		put("Sauerstoff", "Technische Gase");
		put("Schweißargon", "Technische Gase");
		put("Stickstoff", "Technische Gase");
		put("Wasserstoff", "Technische Gase");
		put("Bananengas", "LM Gastronomie");
		put("Gourmet C", "LM Gastronomie");
		put("Gourmet N", "LM Gastronomie");
		put("Gourmet N80/N70", "LM Gastronomie");
		put("Gourmet O", "LM Gastronomie");
		put("Gourmet O80", "LM Gastronomie");
		put("Gourmet Vinocor", "LM Gastronomie");
		put("Trockeneis", "LM Gastronomie");
		put("Argon/Methan", "Standard");
		put("Argon W Spectro", "Standard");
		put("Helium/Wasserstoff 60/40", "Standard");
		put("Lambda Mix", "Standard");
		put("Lasergemisch Rofin DC OXX", "Standard");
		put("Megalas S CO2 Lasergemisch", "Standard");
		put("Megalas für Schneid- und Schweißlaser", "Standard");
		put("Steritox EO/CO2 10/90", "Standard");
		put("Synthetische Luft", "Standard");
		put("Top Fill (Reifenfüllgas)", "Standard");
		put("Hexafluorethan", "Elektronik");
		put("Octafluorpropan", "Elektronik");
		put("Tetrafluormethan", "Elektronik");
		put("Azetylen", "Spezialgase");
		put("Ammoniak", "Spezialgase");
		put("Argon", "Spezialgase");
		put("Butan", "Spezialgase");
		put("Butylen", "Spezialgase");
		put("Chlor", "Spezialgase");
		put("Chlorwasserstoff", "Spezialgase");
		put("Deuterium", "Spezialgase");
		put("Distickstoffoxid", "Spezialgase");
		put("Ethan", "Spezialgase");
		put("Ethylen", "Spezialgase");
		put("Helium", "Spezialgase");
		put("Isobutan", "Spezialgase");
		put("Isobutylen", "Spezialgase");
		put("Kohlendioxid", "Spezialgase");
		put("Kohlenmonoxid", "Spezialgase");
		put("Krypton", "Spezialgase");
		put("Methan", "Spezialgase");
		put("Neon", "Spezialgase");
		put("Propan", "Spezialgase");
		put("Propylen", "Spezialgase");
		put("Sauerstoff", "Spezialgase");
		put("Schwefeldioxid", "Spezialgase");
		put("Schwefelhexafluorid", "Spezialgase");
		put("Stickstoff", "Spezialgase");
		put("Carbogen", "Medizin /Tieftemperatur");
		put("Gemisch High/Low", "Medizin /Tieftemperatur");
		put("CO2 med", "Medizin /Tieftemperatur");
		put("Luft Synth", "Medizin /Tieftemperatur");
		put("Oxivital", "Medizin /Tieftemperatur");
		put("Oxystem", "Medizin /Tieftemperatur");
		put("Pulmokal", "Medizin /Tieftemperatur");
		put("RespiMesser NO", "Medizin /Tieftemperatur");
		put("RespiMesser O2 He", "Medizin /Tieftemperatur");
		put("Sanox", "Medizin /Tieftemperatur");
		put("O2 med", "Medizin /Tieftemperatur");
	}};
	
	static String[] description = {
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
		"Vivamus eu odio in ipsum dapibus rutrum quis condimentum dolor.",
		"Vestibulum et sapien nec mauris ullamcorper sagittis sit amet non augue. Ut fermentum risus et diam feugiat, sed suscipit diam tempus.",
		"Curabitur dui quam, luctus vitae placerat nec, dignissim vel orci. Donec venenatis commodo enim venenatis euismod.",
		"Proin nec imperdiet felis. Proin nec lectus sed magna sagittis vestibulum.",
		"Phasellus facilisis erat vitae leo vehicula ultricies.",
		"Donec venenatis venenatis eros sit amet luctus.",
		"Interdum et malesuada fames ac ante ipsum primis in faucibus.",
		"Etiam at est pharetra, bibendum diam eget, gravida sem.",
		"Etiam velit enim, scelerisque a mauris ut, suscipit ultrices leo.",
		"Fusce fringilla arcu sit amet lacus rhoncus mollis.",
		"Donec congue velit vel lacus finibus, vel porta sem lacinia. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.",
		"Etiam libero ligula, commodo id mauris a, sagittis blandit ligula.",
		"Mauris a rutrum nulla. Cras eget elit fringilla, viverra nibh dignissim, lacinia ex.",
		"Fusce ultricies malesuada neque sed porta. Sed vel lectus eget elit egestas gravida eu eu felis.",
		"Ut lobortis, ligula ut aliquam tincidunt, nulla eros pellentesque lectus, id fermentum libero augue eget odio.",
		"Integer blandit, nunc quis volutpat consequat, nisl orci finibus felis, in molestie eros lacus a purus. Nulla dapibus faucibus ipsum, non blandit dui laoreet sit amet.",
		"Integer fringilla massa nec placerat luctus. Nullam ornare nisl eu sapien efficitur sodales.",
		"Donec nec fermentum sapien, eget venenatis diam. Duis ac sodales purus. Nullam in massa sed ligula rutrum suscipit.",
		"Aenean rutrum vehicula felis, sed volutpat eros interdum ut. Etiam fermentum, massa fermentum rhoncus consequat, arcu est congue nibh, id laoreet urna purus quis enim.",
		"Aenean nec placerat sapien. Aliquam sit amet tristique lectus, sed elementum odio. Nam eget lorem est.",
		"Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin pretium metus libero, eget scelerisque lacus pretium luctus.",
		"Phasellus id lobortis tortor, sed tristique ex. Sed tempus egestas laoreet. Praesent rutrum molestie ligula, vel dictum ipsum ultricies ut.",
		"Curabitur congue volutpat sem, at lacinia felis luctus sit amet. Pellentesque a vulputate massa, vel ullamcorper ex.",
		"Pellentesque blandit porttitor neque a ullamcorper. Nulla aliquet, magna nec mattis porta, lorem risus cursus mauris, et lobortis dui nisl ac diam.",
		"Aliquam erat volutpat. Suspendisse quis scelerisque mi, a ullamcorper augue. Aenean eleifend ultrices leo eu commodo.",
		"Aliquam molestie, lacus sit amet tincidunt tincidunt, lectus nibh congue odio, eget finibus arcu tortor et mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
		"Vestibulum erat lectus, fringilla et pharetra ac, egestas ut leo. Aliquam ipsum leo, auctor a iaculis vulputate, facilisis eu nulla.",
		"Aliquam a nulla ultrices, varius felis eu, rutrum tortor. Praesent ut quam quis metus dignissim condimentum eu et velit.",
		"Suspendisse potenti. Nulla porttitor arcu dui, in sodales ligula mollis sit amet.",
		"Suspendisse in rhoncus magna. Sed quis erat nec magna lacinia ultrices ut id felis.",
		"Cras auctor eros diam, nec semper sapien faucibus ut. Vestibulum nec metus vel lectus placerat consequat. Proin rhoncus orci et neque iaculis accumsan.",
		"Ut at mi nisi. Maecenas at elementum ipsum. Cras aliquam sagittis dui, sed sagittis orci varius sit amet.",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
		"Vivamus eu odio in ipsum dapibus rutrum quis condimentum dolor.",
		"Vestibulum et sapien nec mauris ullamcorper sagittis sit amet non augue. Ut fermentum risus et diam feugiat, sed suscipit diam tempus.",
		"Curabitur dui quam, luctus vitae placerat nec, dignissim vel orci. Donec venenatis commodo enim venenatis euismod.",
		"Proin nec imperdiet felis. Proin nec lectus sed magna sagittis vestibulum.",
		"Phasellus facilisis erat vitae leo vehicula ultricies.",
		"Donec venenatis venenatis eros sit amet luctus.",
		"Interdum et malesuada fames ac ante ipsum primis in faucibus.",
		"Etiam at est pharetra, bibendum diam eget, gravida sem.",
		"Etiam velit enim, scelerisque a mauris ut, suscipit ultrices leo.",
		"Fusce fringilla arcu sit amet lacus rhoncus mollis.",
		"Donec congue velit vel lacus finibus, vel porta sem lacinia. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.",
		"Etiam libero ligula, commodo id mauris a, sagittis blandit ligula.",
		"Mauris a rutrum nulla. Cras eget elit fringilla, viverra nibh dignissim, lacinia ex.",
		"Fusce ultricies malesuada neque sed porta. Sed vel lectus eget elit egestas gravida eu eu felis.",
		"Ut lobortis, ligula ut aliquam tincidunt, nulla eros pellentesque lectus, id fermentum libero augue eget odio.",
		"Integer blandit, nunc quis volutpat consequat, nisl orci finibus felis, in molestie eros lacus a purus. Nulla dapibus faucibus ipsum, non blandit dui laoreet sit amet.",
		"Integer fringilla massa nec placerat luctus. Nullam ornare nisl eu sapien efficitur sodales.",
		"Donec nec fermentum sapien, eget venenatis diam. Duis ac sodales purus. Nullam in massa sed ligula rutrum suscipit.",
		"Aenean rutrum vehicula felis, sed volutpat eros interdum ut. Etiam fermentum, massa fermentum rhoncus consequat, arcu est congue nibh, id laoreet urna purus quis enim.",
		"Aenean nec placerat sapien. Aliquam sit amet tristique lectus, sed elementum odio. Nam eget lorem est.",
		"Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin pretium metus libero, eget scelerisque lacus pretium luctus.",
		"Phasellus id lobortis tortor, sed tristique ex. Sed tempus egestas laoreet. Praesent rutrum molestie ligula, vel dictum ipsum ultricies ut.",
		"Curabitur congue volutpat sem, at lacinia felis luctus sit amet. Pellentesque a vulputate massa, vel ullamcorper ex.",
		"Pellentesque blandit porttitor neque a ullamcorper. Nulla aliquet, magna nec mattis porta, lorem risus cursus mauris, et lobortis dui nisl ac diam.",
		"Aliquam erat volutpat. Suspendisse quis scelerisque mi, a ullamcorper augue. Aenean eleifend ultrices leo eu commodo.",
		"Aliquam molestie, lacus sit amet tincidunt tincidunt, lectus nibh congue odio, eget finibus arcu tortor et mauris. Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
		"Vestibulum erat lectus, fringilla et pharetra ac, egestas ut leo. Aliquam ipsum leo, auctor a iaculis vulputate, facilisis eu nulla.",
		"Aliquam a nulla ultrices, varius felis eu, rutrum tortor. Praesent ut quam quis metus dignissim condimentum eu et velit.",
		"Suspendisse potenti. Nulla porttitor arcu dui, in sodales ligula mollis sit amet.",
		"Suspendisse in rhoncus magna. Sed quis erat nec magna lacinia ultrices ut id felis.",
		"Cras auctor eros diam, nec semper sapien faucibus ut. Vestibulum nec metus vel lectus placerat consequat. Proin rhoncus orci et neque iaculis accumsan.",
		"Ut at mi nisi. Maecenas at elementum ipsum. Cras aliquam sagittis dui, sed sagittis orci varius sit amet."
	};

    private static ProductService instance;

    public static ProductService createDemoService() {
        if (instance == null) {

            final ProductService productService = new ProductService();

            Random r = new Random(0);

            for(Map.Entry<String, String> entry : gases.entrySet()){
                Product product = new Product();
                product.setProductId(Integer.toString(r.nextInt(5)) 
                		+ Integer.toString(r.nextInt(5)) 
                		+ "-" 
                		+ Integer.toString(r.nextInt(5)) 
                		+ Integer.toString(r.nextInt(5)) 
                		+ Integer.toString(r.nextInt(5)));
                product.setProductName(entry.getKey());
                product.setCategory(entry.getValue());
                
                int vol = r.nextInt(5);
                while(vol == 0){
                	vol = r.nextInt(5);
                }
                
                product.setSize(Integer.toString(vol) + "0 lt");
                product.setDescription(description[r.nextInt(description.length)]);
                
                productService.save(product);
            }
            
            instance = productService;
        }

        return instance;
    }

    private HashMap<Long, Product> products = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Product> findAll(String stringFilter) {
        ArrayList<Product> arrayList = new ArrayList<Product>();
        for (Product Product : products.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || Product.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(Product.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ProductService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Product>() {

            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return products.size();
    }

    public synchronized void delete(Product value) {
        products.remove(value.getId());
    }

    public synchronized void save(Product entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Product) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        products.put(entry.getId(), entry);
    }

}

