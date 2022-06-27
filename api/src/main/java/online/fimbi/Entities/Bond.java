package online.fimbi.Entities;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.DotDto;
import online.fimbi.Dto.GraphDto;

@Entity
@Table(name = "bond")
@Data
@NoArgsConstructor

public class Bond {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	// has only one issuer
	@ManyToOne
	@JoinColumn(name = "issuer_id", insertable = false, updatable = false)
	private Issuer issuer;

	// can have none or many relations with users
	// as long as is less than `splits`
	@OneToMany
	@JoinColumn(name = "bond_id") // we need to duplicate the physical information
	@Size(min = 0, max = 100)
	private Set<UserxBond> relations;

	@Column(nullable = false)
	@Max(100)
	int splits;

	@Column(nullable = false)
	Long issuer_id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date emission_date;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date due_date;

	@Column(nullable = false)
	Long total_value;

	@Column(nullable = false)
	Long nominal_value;

	@Column(nullable = false)
	float interest_rate;

	@Column(nullable = false)
	float capitalization_rate;

	@Column(nullable = false, length = 32)
	String external_interest_rate;

	@Column(nullable = false)
	int available;

	@Column(nullable = false)
	Long public_value;

	@Column(nullable = false)
	float income_tax;

	@Column(nullable = false)
	float prime_vcto;

	@Column(nullable = false)
	float struct;

	@Column(nullable = false)
	float colocacion;

	@Column(nullable = false)
	float floating;

	@Column(nullable = false)
	float cavali;

	@Column(nullable = false)
	float tea_coupon;

	@Column(nullable = false)
	float yield_to_mat;

	@Column(nullable = false)
	float coupon_val;

	@Column(nullable = false)
	float return_rate; // IRR

	@Column(nullable = false)
	float present_value; // NPV

	@Column(nullable = false)
	int periods;

	private int error;
	private float[] cash_flow;

	public Bond(BondDto bondDto, Long issuer_id) {
		// Daily: 1
		// Monthly: 2
		// Anually: 3
		this.emission_date = new Date(); // today
		this.splits = bondDto.getSplits();
		this.issuer_id = issuer_id;
		this.due_date = bondDto.getDue_date();
		this.total_value = bondDto.getTotal_value();
		this.nominal_value = Long.valueOf(Math.round(this.total_value / this.splits));
		this.interest_rate = bondDto.getInterest_rate();
		this.capitalization_rate = bondDto.getCapitalization_rate();
		this.external_interest_rate = bondDto.getExternal_interest_rate();
		this.splits = bondDto.getSplits();
		this.available = 1;

		this.public_value = Math.round(this.nominal_value * 1.01);
		this.income_tax = 0.25f / 100.0f;
		this.prime_vcto = 1.0f / 100.0f;
		this.struct = 0.450f / 100.0f;
		this.colocacion = 0.250f / 100.0f;
		this.floating = 0.150f / 100.0f;
		this.cavali = 0.500f / 100.0f;
		this.tea_coupon = 7.5f / 100.0f;
		this.yield_to_mat = 6.0f / 100.0f;

		this.coupon_val = this.nominal_value * this.tea_coupon;
		int periods = 0;
		if (this.capitalization_rate == 3) {
			periods = this.due_date.getYear() - this.emission_date.getYear();
		} else if (this.capitalization_rate == 2) {
			periods = (1 + (int) ChronoUnit.DAYS.between(
					this.emission_date.toInstant(),
					this.due_date.toInstant()) / 30);
		} else if (this.capitalization_rate == 1) {
			periods = 1 + (int) ChronoUnit.DAYS.between(
					this.emission_date.toInstant(),
					this.due_date.toInstant());
		}

		if (periods < 2) {
			error = 1;
			return;
		}

		this.periods = periods;
		update_important_values();
		System.out.println(String.format("%d periods initialized", periods));
	}

	public void update_important_values() {
		gen_cash_flow();
		update_irr();
		update_npv();
	}

	void gen_cash_flow() {
		// SHOULD BE STORED ON DATABASE AND NOT PERFORMED EVERY TIME
		this.cash_flow = new float[this.periods];
		// eq: 0 = sum( c / (1 + IRR)^i )
		this.cash_flow[0] = -(this.public_value - (this.public_value * (this.floating + this.cavali)));
		for (int i = 1; i < this.periods - 1; i++) {
			this.cash_flow[i] = this.coupon_val;
		}
		this.cash_flow[this.periods - 1] = this.coupon_val + this.nominal_value
				+ (this.nominal_value * this.prime_vcto);
	}

	public void update_irr() {
		// 1 - ( 1 + i)^n / i = fc[0]/cupon
		float obj = -1 * this.cash_flow[0] / this.coupon_val / 100;

		float best_guess = 0.0f;
		float best_result = 0.0f;
		float best_difference = Integer.MAX_VALUE;

		for (float i = 0.00f; i < 15.0f; i += 0.005f) {
			float current = (float) (1 - Math.pow(1 + i / 100.0f, -(this.periods - 1))) / i;
			float diff = Math.abs(obj - current);
			if (diff < best_difference) {
				best_difference = diff;
				best_result = current;
				best_guess = i;
			}
		}
		this.return_rate = best_guess;
		System.out.println(String.format("IRR: %f I/C: %f Res: %f",
				this.return_rate, obj, best_result));
	}

	public void update_npv() {
		// update on jpa missing
		// fix in service TODO

		float TIR = this.return_rate / 100;
		float NPV = 0.0f;

		System.out.println("NPV over time:");

		for (int i = 0; i < this.periods; i++) {
			NPV += (float) this.cash_flow[i] / (float) Math.pow(1 + TIR, i);
		}

		this.present_value = NPV;
		System.out.println("NPV: " + this.present_value);
	}

	public GraphDto getCashFlow() {
		// Daily: 1
		// Monthly: 2
		// Anually: 3
		this.gen_cash_flow();
		GraphDto graph = new GraphDto();
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.emission_date);
		for (int i = 0; i < this.periods; i++) {
			if (this.capitalization_rate == 1) {
				cal.add(Calendar.DATE, 1);
			} else if (this.capitalization_rate == 2) {
				cal.add(Calendar.MONTH, 1);
			} else if (this.capitalization_rate == 3) {
				cal.add(Calendar.YEAR, 1);
			}
			graph.data.add(new DotDto(cal.getTime(), cash_flow[i]));

		}
		return graph;
	}
}
