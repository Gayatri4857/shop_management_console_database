package product_management;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import db_operations.DBUtils;


public class ProductManagement {
	
	public static void productManagement() throws IOException{ 
		
		Scanner scanner = new Scanner(System.in);
		boolean canikeeprunningprogram = true;
		while(canikeeprunningprogram == true) {
			System.out.println("##WELLCOME TO PRODUCT MANAGEMENT##");
			System.out.println("1. Add Product:");
			System.out.println("2. Edit Product:");
			System.out.println("3. Delete Product:");
			System.out.println("4. Search Product:");
			System.out.println("5. Quit Product:");
			System.out.println("\n");
			
			int optionselectedbyproduct = scanner.nextInt();
			if(optionselectedbyproduct == ProductOptions.QUIT_PRODUCT) {
				
				System.out.println("!!! Program closed..");
				System.out.println("\n");
				canikeeprunningprogram = false;
			}
			else if(optionselectedbyproduct == ProductOptions.ADD_PRODUCT) {
				addProduct();
				System.out.println("\n");
			}
			else if(optionselectedbyproduct == ProductOptions.SEARCH_PRODUCT) {
				System.out.println("Enter the productname to Search:");
				scanner.nextLine();
				String searchproductname = scanner.nextLine();
				SearchProduct(searchproductname);
				System.out.println("\n");
			}
			else if(optionselectedbyproduct == ProductOptions.EDIT_PRODUCT) {
				System.out.println("Enter the productname to Edit:");
				scanner.nextLine();
				String editproductname = scanner.nextLine();
				EditProduct(editproductname);
				System.out.println("\n");
			}
			else if(optionselectedbyproduct == ProductOptions.DELETE_PRODUCT) {
				System.out.println("Enter the productname to Delete:");
				scanner.nextLine();
				String deleteproductname = scanner.nextLine();
				DeleteProduct(deleteproductname);
				System.out.println("\n");
			}
		}
	}
	public static void addProduct() {
		    Scanner scanner = new Scanner(System.in) ;
			Product productObject = new Product();
			System.out.println("Product name:");
			productObject.productname =scanner.nextLine();
			System.out.println("Product Id:");
			productObject.productid =scanner.nextLine();
			System.out.println("Product price:");
			productObject.productprice = scanner.nextLine();
			System.out.println("Product Quality:");
			productObject.productquality = scanner.nextLine();
			System.out.println("Product Category:");
			productObject.productcategory = scanner.nextLine();
			
			System.out.println("Product name :"+productObject.productname);
			System.out.println("Product Id is:"+productObject.productid);
			System.out.println("Product Price:"+productObject.productprice);
			System.out.println("Product Quality:"+productObject.productquality);
			System.out.println("Product Category:"+productObject.productcategory);
			
			
			String query = "insert INTO product(ProductName,ProductID,ProductPrice,ProductQuality,ProductCategory)Values('"+productObject.productname+"','"+productObject.productid+"','"+productObject.productprice+"','"+productObject.productquality+"','"+productObject.productcategory+"')";
		    DBUtils.executeQuery(query);
	}
	
	public static void SearchProduct(String productname) {
	String query = "select * from product where ProductName ='"+productname+"' ";
	ResultSet rs = DBUtils.executeQueryGetResult(query);
	
	try{
		while(rs.next()){
			if(rs.getString("ProductName").equalsIgnoreCase(productname)){
		
				System.out.println("Product name:"+rs.getString("ProductName"));
				System.out.println("Product Id:"+rs.getString("ProductID"));
				System.out.println("Product Price:"+rs.getString("ProductPrice"));
				System.out.println("Product Quality:"+rs.getString("ProductQuality"));
				System.out.println("Product Category:"+rs.getString("ProductCategory"));
				return;
			}
		}
	}
	catch(Exception e) {
		System.out.println("Product not found!!!!");
	}
	System.out.println("User not found!!");
	}
	
	
	public static void EditProduct(String productname) {
	String query = "select * from product where ProductName = '"+productname+"'";
	
	ResultSet rs = DBUtils.executeQueryGetResult(query);
	try{
		while(rs.next()){
			if(rs.getString("ProductName").equalsIgnoreCase(productname))
		
		{
				Scanner scanner = new Scanner(System.in);
				Product product = new Product();
				
				
				System.out.println("New Product name:");
				product.productname = scanner.nextLine();
				System.out.println("New Product Id:");
				product.productid = scanner.nextLine();
				System.out.println("New Product Price:");
				product.productprice= scanner.nextLine();
				System.out.println("New Product Quality:");
				product.productquality = scanner.nextLine();
				System.out.println("New Product Category:");
				product.productcategory = scanner.nextLine();
				
				String updateQuery = "update product set "
						+ "ProductName='"+product.productname+"', ProductID ='"+product.productid+"', "
						+ "ProductPrice='"+product.productprice+"', ProductQuality='"+product.productquality+"', "
								+ "ProductCategory='"+product.productcategory+"' where ProductName='"+rs.getString("productname")+"'";
				
				
				DBUtils.executeQuery(updateQuery);
				System.out.println("Product updated successfully");
				return;

			}
			else {
		
		System.out.println("Product not found!!!");
	}
		}
		}
		catch(SQLException e) {
			
			System.out.println("Product not found");
		}
		System.out.println("Product not found.");
	}

	public static void DeleteProduct(String productname) {
		String query = "select from product where ProductName ='"+ productname +"' ";
		DBUtils.executeQuery(query);
	}
}
		
	