package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Url {


	/*
	 *  wieviele Seiten sollen extrahiert werden
	 *  minimal 2
	 */
	private static Integer seiten = 5;
	
	/*
	 * online true
	 * offline false
	 */
	private static boolean online = true;
	
	public static String review = "";
	
	public static List<String> reviewTexte = new ArrayList<String>();
	
	public static List<String> reviewTextToFile() throws IOException, InterruptedException {

		BufferedReader br = new BufferedReader(new FileReader("src/texts/Quelltext.txt"));
		
		// Review Texte ziehen
		String string;
		String string1;
		String string2;
		StringBuilder sb = new StringBuilder();
		 while((string = br.readLine()) != null) {
			if (string.contains("<span class=\"a-size-base review-text\">")) {
				
				//TODO: Falsches Regex
				string1 = string.replaceAll("<div id=\"cm_cr-review_list\" class=\"a-section a-spacing-none reviews celwidget\">.*?<span class=\"a-size-base review-text\">", "");
			   string2 = string1.replaceAll("</span></div><div class=\"a-row a-spacing-top-small review-comments\">.*?<ul class=\"a-viewoptions-list a-viewoptions-section a-span12\">", "");
				reviewTexte.add(string1);
				sb.append(string1);
				sb.append("\n");
			}
		}
		 br.close();
//		 <div class="a-section a-spacing-extra-large a-spacing-top-medium a-text-center comment-load-error a-hidden"><div class="a-box a-alert a-alert-error cr-error"><div class="a-box-inner a-alert-container"><h4 class="a-alert-heading">There was a problem loading comments right now. Please try again later.</h4><i class="a-icon a-icon-alert"></i><div class="a-alert-content"></div></div></div></div><div class="a-section a-spacing-none review-comments"></div><div class="a-section a-spacing-extra-large a-spacing-top-medium a-text-center comment-loading a-hidden"></div><span class="a-declarative" data-action="reviews:more-comments" data-reviews:more-comments="{}"><span class="a-button a-button-small more-comments-button a-hidden"><span class="a-button-inner"><input data-reftag="cm_cr_pr_cmt_btn" class="a-button-input" type="submit"><span class="a-button-text" aria-hidden="true">Show more comments</span></span></span></span><hr class="a-spacing-none a-spacing-top-large a-divider-normal"></div></div></div></div><div id="RX8BPC2GP8FUC" class="a-section review"><div class="a-row helpful-votes-count"></div><div class="a-row"><a class="a-link-normal" href="/gp/customer-reviews/RX8BPC2GP8FUC/ref=cm_cr_pr_rvw_ttl?ie=UTF8&ASIN=B00166ZFXO"><i class="a-icon a-icon-star a-star-5 review-rating"><span class="a-icon-alt">5</span></i></a><span class="a-letter-space"></span><a class="a-size-base a-link-normal review-title a-color-base a-text-bold" href="/gp/customer-reviews/RX8BPC2GP8FUC/ref=cm_cr_pr_rvw_ttl?ie=UTF8&ASIN=B00166ZFXO">happy with these</a></div><div class="a-row"><span class="a-size-base a-color-secondary review-byline"><span class="a-color-secondary">By</span><span class="a-letter-space"></span><a class="a-size-base a-link-normal author" href="/gp/pdp/profile/AH37UAJDRLZ5C/ref=cm_cr_pr_pdp?ie=UTF8">Ian</a></span><span class="a-declarative" data-action="cr-popup" data-cr-popup="{&quot;width&quot;:&quot;340&quot;,&quot;title&quot;:&quot;Help&quot;,&quot;url&quot;:&quot;/gp/help/customer/display.html/ref=cm_cr_dp_bdg_help?ie=UTF8&amp;nodeId=14279681&amp;pop-up=1#tr&quot;,&quot;height&quot;:&quot;340&quot;}"></span><span class="a-letter-space"></span><span class="a-size-base a-color-secondary review-date">on March 4, 2015</span></div><div class="a-row a-spacing-mini review-data"><span class="a-declarative" data-action="reviews:show-avp" data-reviews:show-avp="{&quot;allowLinkDefault&quot;:&quot;1&quot;}"><a data-reftag="cm_cr_pr_rvw_rvwer" class="a-link-normal" href="/JBL-ES80BK-6-Inch-Floorstanding-Speaker/product-reviews/B00166ZFXO/ref=cm_cr_pr_rvw_rvwer?ie=UTF8&filterByStar=all_stars&reviewerType=avp_only_reviews&sortBy=helpful"><span class="a-size-mini a-color-state a-text-bold">Verified Purchase</span></a></span></div><div class="a-row review-data"><span class="a-size-base review-text">
		review = sb.toString();
//		System.out.println("String " + review);
		 
		File file = new File("src/texts/reviews.txt");
		FileWriter out = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(out);

		
//		// Review Texte toFile
		Iterator<String> iteratorReviewTexte = reviewTexte.iterator();
		while (iteratorReviewTexte.hasNext()) {
			String str = iteratorReviewTexte.next();
				bw.write(str);
				bw.newLine();
			}	
			bw.close();
			return reviewTexte;
	}

	public static List<String> reviewToToken() throws IOException {
		
		File fileIn = new File("src/texts/reviews.txt");
		String encoding = "UTF-8";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn), encoding));
		StringBuffer sb = new StringBuffer();
		
		File fileList = new File("src/texts/reviewsList.txt");
		FileWriter out = new FileWriter(fileList);
		BufferedWriter bw = new BufferedWriter(out);
		
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}
		br.close();
		BreakIterator iterator = BreakIterator.getWordInstance();
		String text = sb.toString();
		iterator.setText(text);
		List<String> words = new ArrayList<String>();
		int last = 0;
		while(true) {
			int next = iterator.next();
			if(next == -1) break;
			String sub = text.substring(last, next).trim();
			if(sub.length() == 0) continue;
			words.add(sub);
			last = next;
		}
		
		// Liste schreiben
//		
		for (String word : words) {
			// kleiner Filter
			if(word.length() > 2) {
				bw.write(word);
				bw.newLine();
			}
			else {}
		}
		bw.close(); br.close(); out.close();
		
		return null;
	}
}
